package ndtq.controller;
import ndtq.model.*;
import ndtq.service.Songs.ISongService;
import ndtq.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IUserService userService;

    @GetMapping("/listNewSongs")
    public ResponseEntity<Iterable<Songs>> listNewSongsByDate() {
        return new ResponseEntity<>(iSongService.listNewSongs(), HttpStatus.OK);
    }
    @GetMapping("/listSongsTrending")
    public ResponseEntity<Iterable<Songs>> listSongsTrendingByView() {
        return new ResponseEntity<>(iSongService.listTrending(), HttpStatus.OK);
    }

    @GetMapping("/listTop10SongsTrending")
    public ResponseEntity<Iterable<Songs>> listTop10SongsTrending() {
        return new ResponseEntity<>(iSongService.listTop10SongsTrending(), HttpStatus.OK);
    }

    @GetMapping("/listSongsTrendingAsc")
    public ResponseEntity<Iterable<Songs>> listSongsTrendingByViewAsc() {
        return new ResponseEntity<>(iSongService.listTrendingAsc(), HttpStatus.OK);
    }
    @GetMapping("/listTop10SongsLikeTrending")
    public ResponseEntity<Iterable<Songs>> listTop10SongsLikeTrending() {
        return new ResponseEntity<>(iSongService.listTop10SongsLikeTrending(), HttpStatus.OK);
    }

    @GetMapping("/listSongsByUser/{id}")
    ResponseEntity<Iterable<Songs>> listSongsByUser(@PathVariable("id") Long idUser) {
        return new ResponseEntity<>(iSongService.findAllByUsers(userService.findById(idUser).get()), HttpStatus.OK);
    }
    @PostMapping()
    ResponseEntity<?> save(@RequestBody Songs songs) {
        songs.setDate(LocalDateTime.now());
        iSongService.save(songs);

        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteSongs(@PathVariable("id") Long idSongs) {
        iSongService.remove(idSongs);
        return new ResponseEntity<> (HttpStatus.OK);
    }
    @GetMapping("{id}")
    ResponseEntity<Optional<Songs>> findSongById(@PathVariable("id") Long idSong) {
        return new ResponseEntity<>(iSongService.findById(idSong), HttpStatus.OK);
    }
    @PutMapping("{id}")
    ResponseEntity<Optional<Songs>> updateSongs(@PathVariable("id") Long idSong,@RequestBody Songs newSongs) {
        Songs oldSong=iSongService.findById(idSong).get();
        newSongs.setId(idSong);
        newSongs.setViews(oldSong.getViews());
        newSongs.setDate(oldSong.getDate());
        newSongs.setUserLikeSong(oldSong.getUserLikeSong());
        iSongService.save(newSongs);
        return new ResponseEntity<>(iSongService.findById(idSong), HttpStatus.OK);
    }
    @GetMapping("/suggest/{idSongNow}")
    ResponseEntity<Iterable<Songs>> suggest5Songs(@PathVariable("idSongNow") Long idSongNow) {
        return new ResponseEntity<>(iSongService.suggest5Songs(idSongNow), HttpStatus.OK);
    }
    @PutMapping("/like")
    ResponseEntity<Iterable<Songs>> changeLike(@RequestBody Songs songs) {
        iSongService.save(songs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/singer/{id}")
    public ResponseEntity<Iterable<Songs>> getAllSongBySinger(@PathVariable Long id) {
        return new ResponseEntity<>(iSongService.findAllBySingerList(id), HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Iterable<Songs>> getAllSongByTag(@PathVariable Long id) {
        return new ResponseEntity<>(iSongService.getAllSongByTag(id), HttpStatus.OK);
    }
}


