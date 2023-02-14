package ndtq.repository;


import ndtq.model.Playlist;
import ndtq.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
//TODO extends Jpa thì mặc định Transactional được tạo kèm theo, nếu không có các property đi kèm thì không cần thiết đặt ở đây
// típ: hãy dùng nó ở layer service để quản lý nghiệp vụ, rollback hoặc commit nó càng sớm càng tốt.
@Transactional
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findAllByOrderByViewsDesc();
    Iterable<Playlist> findAllByOrderByDateCreateDesc();
    Iterable<Playlist> findAllByUsers(Users users);
    Iterable<Playlist> findAllByNameContaining(String name);
@Modifying
    @Query(value = "update Playlist set views=(views+ 1)",nativeQuery = true)
    void setViewsAllPlaylist();

//TODO name Schema sử dụng ở đây là không cần thiết khi sử dụng ORM là JPA đã hỗ trợ mình rồi
@Modifying
    @Query(value="DELETE FROM casestudy4.playlist_tag WHERE id_playlist = ?1", nativeQuery = true)
    void deletePlaylistInTag(Long idPlaylist);
@Modifying
    @Query(value="DELETE FROM casestudy4.playlist_song WHERE id_playlist = ?1", nativeQuery = true)
    void deletePlaylistInSongs(Long idPlaylist);
}
