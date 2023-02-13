package ndtq.service.comment;


import ndtq.model.Comments;
import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comments> {
    Iterable<Comments> findAllBySongsOrderByDateDesc(Songs songs);
    Iterable<Comments> findAllByPlaylistOrderByDateDesc(Playlist playlist);
}
