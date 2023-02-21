package ndtq.controller;


import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.service.Songs.ISongService;
import ndtq.service.Tags.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private ITagService iTagService;

    @GetMapping("/findSongsByTag/{id}")
    ResponseEntity<Iterable<Songs>> listSongsByTagsList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iTagService.listSongByTag(id), HttpStatus.OK);
    }

    @GetMapping("/findPlaylistByTag/{id}")
    ResponseEntity<Iterable<Playlist>> listPlaylistsByTagsList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iTagService.listPlaylistByTag(id), HttpStatus.OK);
    }
    @GetMapping("/hint5Tags")
    ResponseEntity<Iterable<Tags>> hint5Tags() {
        return new ResponseEntity<>(iTagService.hint5Tags(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Tags>> getAllTag() {
        return new ResponseEntity<>(iTagService.get15Tag(), HttpStatus.OK);
    }

}
