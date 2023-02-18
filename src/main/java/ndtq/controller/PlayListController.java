package ndtq.controller;
import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.service.playlist.IPlaylistService;
import ndtq.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {
    @Autowired
    private final IPlaylistService playlistService;
    @Autowired
    private IUserService userService;

    @Autowired
    public PlayListController(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/top")
    public ResponseEntity<Iterable<Playlist>> getTopPlaylist() {
        return new ResponseEntity<>(playlistService.listTrending(), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<Iterable<Playlist>> getNewPlaylist() {
        return new ResponseEntity<>(playlistService.listNewPlaylist(), HttpStatus.OK);
    }

    @GetMapping("/listTop10ViewsPlaylistTrending")
    public ResponseEntity<Iterable<Playlist>> getTop10ViewsPlaylist() {
        return new ResponseEntity<>(playlistService.listTop10ViewsPlaylistTrending(), HttpStatus.OK);
    }

    @GetMapping("/listTop10PlaylistOrderByDateDesc")
    public ResponseEntity<Iterable<Playlist>> getTop10PlaylistOrderByDateDesc () {
        return new ResponseEntity<>(playlistService.listTop10PlaylistOrderByDateDesc(), HttpStatus.OK);
    }
    @GetMapping("/findPlaylistByUser/{id}")
    ResponseEntity<Iterable<Playlist>> listSongsByUser(@PathVariable("id") Long idUser) {
        return new ResponseEntity<>(playlistService.findAllByUsers(userService.findById(idUser).get()), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Playlist playlist) {
        LocalDate date_create = LocalDate.now();
        LocalDate last_update = LocalDate.now();
        Playlist newUser = new Playlist(playlist.getName(), playlist.getDescription(), playlist.getAvatar(), date_create, last_update, playlist.getUsers(),playlist.getTagsList(), 200);
        playlistService.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long idPlaylist) {
        playlistService.remove(idPlaylist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Optional<Playlist>> findPlaylistById(@PathVariable("id") Long idPlaylist) {
        return new ResponseEntity<>(playlistService.findById(idPlaylist),HttpStatus.OK);
    }
    @PutMapping("{id}")
    ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Long idPlaylist, @RequestBody Playlist playlist) {
        Playlist oldPlaylist= playlistService.findById(idPlaylist).get();
        playlist.setId(idPlaylist);
        playlist.setDateCreate(oldPlaylist.getDateCreate());
        playlist.setLastUpdate(LocalDate.now());
        playlist.setUserLikesPlaylist(oldPlaylist.getUserLikesPlaylist());
        playlist.setViews(oldPlaylist.getViews());
        playlistService.save(playlist);
        return new ResponseEntity<>(playlistService.save(playlist),HttpStatus.OK);
    }

    @GetMapping("/topLikePlaylist")
    public ResponseEntity<Iterable<Playlist>> getTopLikePlaylist() {
        return new ResponseEntity<>(playlistService.findTopLikePlaylist(),HttpStatus.OK);
    }
    @PutMapping("/like")
    ResponseEntity<Iterable<Songs>> changeLike(@RequestBody Playlist playlist) {
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/changeSongToPlaylist")
    ResponseEntity<Iterable<Songs>> changeSongToPlaylist(@RequestBody Playlist playlist) {
        playlist.setLastUpdate(LocalDate.now());
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
