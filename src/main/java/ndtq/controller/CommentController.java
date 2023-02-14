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
//TODO có thể config CrossOrigin bằng cách tạo bean và inject vào cả project ko phải thủ công từng controller như này
// ( keyword:  Global CORS Configuration or  Spring Security CORS config)
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    //TODO hạn chế inject bằng @Autowired sẽ khó kiểm soát khi viết các testcase
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;
    //TODO Khi viết api thì nên tạo chung 1 định dạng ở body trả về cho fe dễ xử lý.
    // ví dụ {
    //    "message": "kết quả theo dạng chữ trả về",
    //    "data": [object dữ liệu trả ra mong muốn dạng json ],
    //    "requestId": "unique id để dễ trace log và tìm khi debug",
    //       }
    // Chỉ trả về Đúng và Đủ các trường theo yêu cầu tránh trường hợp lấy ra hết các dữ liệu không cần thiết sẽ khiến thời gian và dung lượng truyền tải dữ liệu bị tăng theo
    // ví dụ trường hợp api dưới nếu chỉ cần lấy ra comment ở bài hát và nghiệp vụ k cần thiết lấy ra thông tin playlist thì trường hợp này đang bị thừa dữ liệu
    // (keyword: DTO Pattern)
    @GetMapping("/song/{id}")
    public ResponseEntity<Iterable<Comments>> listCommentInSong(@PathVariable Long id) {
        return new ResponseEntity<>(iCommentService.findAllBySongsOrderByDateDesc(iSongService.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/playlist")
    public ResponseEntity<Iterable<Comments>> listCommentInPlaylist(@RequestBody Long id) {
        return new ResponseEntity<>(iCommentService.findAllByPlaylistOrderByDateDesc(iPlaylistService.findById(id).get()), HttpStatus.OK);
    }
    //TODO chưa có endpoint cho actions này
    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody Long id) {
        iCommentService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //TODO chưa có endpoint cho actions này
    // @RequestBody Comments comments ko nên sử dụng entity để làm input sẽ khó cho fe xử lý để lấy hết dữ liệu của chúng:
    // ví dụ: để make 1 Object Comments thì fe cần phải lấy đủ các object Users , Songs , Playlist... thay vì như vậy thì hãy để BE làm bằng việc truyền nên các id đại diện cần thiết cho entity
    // và sau đó BE sẽ make entiy -> save xuống layer Database sau
    //  ex:
    // {
    //    "date": 14022023,
    //    "content": "content",
    //    "user_id": 1,
    //    "song_id": 1,
    //    "playlist_id": 1
    // }
    // (keyword: DTO Pattern)
    @PostMapping
    public ResponseEntity<Iterable<Comments>> save(@RequestBody Comments comments) {
        LocalDateTime date = LocalDateTime.now();
        comments.setDate(date);
        iCommentService.save(comments);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
