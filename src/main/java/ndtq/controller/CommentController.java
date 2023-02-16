package ndtq.controller;

import ndtq.model.Comments;
import ndtq.service.Songs.ISongService;
import ndtq.service.comment.ICommentService;
import ndtq.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;

    @GetMapping("/song/{id}")
    public ResponseEntity<Iterable<Comments>> listCommentInSong(@PathVariable Long id) {
        return new ResponseEntity<>(iCommentService.findAllBySongsOrderByDateDesc(iSongService.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/playlist/{id}")
    public ResponseEntity<Iterable<Comments>> listCommentInPlaylist(@PathVariable Long id) {
        return new ResponseEntity<>(iCommentService.findAllByPlaylistOrderByDateDesc(iPlaylistService.findById(id).get()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody Long id) {
        iCommentService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Iterable<Comments>> save(@RequestBody Comments comments) {
        LocalDateTime date = LocalDateTime.now();
        comments.setDate(date);
        iCommentService.save(comments);
        if(comments.getSongs()!=null){
            return  new ResponseEntity<>(iCommentService.findAllBySongsOrderByDateDesc(comments.getSongs()),HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(iCommentService.findAllByPlaylistOrderByDateDesc(comments.getPlaylist()),HttpStatus.OK);
        }
    }
}
