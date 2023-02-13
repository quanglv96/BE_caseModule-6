package ndtq.controller;
import ndtq.model.Playlist;
import ndtq.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {
    private final IPlaylistService playlistService;

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
}
