package ndtq.controller;


import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.model.Users;
import ndtq.service.Singer.ISingerService;
import ndtq.service.Songs.ISongService;
import ndtq.service.Tags.ITagService;
import ndtq.service.playlist.IPlaylistService;
import ndtq.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ITagService iTagService;


    @GetMapping
    public ResponseEntity<Object> search(@RequestParam("search") String text){
        List<Object> resultSearch=new ArrayList<>();
        resultSearch.add(findSongByName(text));
        resultSearch.add(findPlaylistByName(text));
        resultSearch.add(findUserByName(text));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }
    @GetMapping("/songs")
    public Iterable<Songs> findSongByName(@RequestParam("search") String text){
        return iSongService.findAllByNameContaining(text);
    }
    @GetMapping("/playlist")
    public Iterable<Playlist> findPlaylistByName(@RequestParam("search") String text){
        return iPlaylistService.findAllByNameContaining(text);
    }
    @GetMapping("/user")
    public Iterable<Users> findUserByName(@RequestParam("search") String text){
        return iUserService.findAllByNameContaining(text);
    }
    @GetMapping("/songsByUser")
    public ResponseEntity<Object> findSongByUser(@RequestParam("idUser") Long idUser){
        List<Object> resultSearch=new ArrayList<>();
        resultSearch.add(iUserService.findById(idUser).get());
        resultSearch.add(iSongService.findAllByUsers(iUserService.findById(idUser).get()));
        resultSearch.add(iPlaylistService.findAllByUsers(iUserService.findById(idUser).get()));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }
    @GetMapping("/songsBySinger")
    public ResponseEntity<Iterable<Songs>> findSongBySinger(@RequestParam("idSinger") Long idSinger){
        return new ResponseEntity<>(iSongService.findAllBySingerList(idSinger),HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Object> getAllPlaylistAndSongByTag(@PathVariable Long id) {
        List<Object> playAndSong = new ArrayList<>();
//        playAndSong.add(iPlaylistService.findPlaylistByTags(id));
//        playAndSong.add(iSongService.getAllSongByTag(id));
        playAndSong.add(iTagService.listPlaylistByTag(id));
        playAndSong.add(iTagService.listSongByTag(id));
        return new ResponseEntity<>(playAndSong, HttpStatus.OK);
    }
}
