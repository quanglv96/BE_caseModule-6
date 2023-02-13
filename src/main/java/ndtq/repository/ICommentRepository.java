package ndtq.repository;


import ndtq.model.Comments;
import ndtq.model.Playlist;
import ndtq.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comments, Long> {
    Iterable<Comments> findAllBySongsOrderByIdDesc(Songs songs);
    Iterable<Comments> findAllByPlaylistOrderByIdDesc(Playlist playlist);

}
