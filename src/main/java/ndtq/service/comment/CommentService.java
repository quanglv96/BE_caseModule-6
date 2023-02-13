package ndtq.service.comment;


import ndtq.model.Comments;
import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.repository.ICommentRepository;
import ndtq.service.Songs.ISongService;
import ndtq.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;

    @Override
    public Iterable<Comments> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Optional<Comments> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comments save(Comments comments) {
        return iCommentRepository.save(comments);
    }

    @Override
    public void remove(Long id) {
        iCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comments> findAllBySongsOrderByDateDesc(Songs songs) {
        return iCommentRepository.findAllBySongsOrderByIdDesc(songs);
    }

    @Override
    public Iterable<Comments> findAllByPlaylistOrderByDateDesc(Playlist playlist) {
        return iCommentRepository.findAllByPlaylistOrderByIdDesc(playlist);
    }

}
