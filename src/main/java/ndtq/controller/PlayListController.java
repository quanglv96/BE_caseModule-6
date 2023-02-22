package ndtq.controller;
import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.service.playlist.IPlaylistService;
import ndtq.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
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
        playlist.setDateCreate(LocalDateTime.now());
        playlist.setLastUpdate(LocalDateTime.now());
        playlistService.save(playlist);
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
        playlist.setSongsList(oldPlaylist.getSongsList());
        playlist.setDateCreate(oldPlaylist.getDateCreate());
        playlist.setLastUpdate(LocalDateTime.now());
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
        playlist.setLastUpdate(LocalDateTime.now());
        if(playlist.getId()==null){
            playlist.setAvatar("https://thumbs.dreamstime.com/b/music-collection-line-icon-playlist-outline-logo-illustr-illustration-linear-pictogram-isolated-white-90236357.jpg");
            playlist.setDateCreate(LocalDateTime.now());
        }
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
