package ndtq.controller;


import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.model.Users;
import ndtq.service.Singer.ISingerService;
import ndtq.service.Songs.ISongService;
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

    //TODO hạn chế xử lý logic theo kiểu callback funtion như này trong controller, sau này khi dự án lớn với nhiều nghiệp vụ khác nhau sẽ khó kiểm soát được các lỗi ở runtime
    // hãy để service xử lý logic nghiệp vụ dự án, layer controller nên chỉ phục vụ cho việc điều hướng để đảm bảo nguyên tắc SOLID
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
}
