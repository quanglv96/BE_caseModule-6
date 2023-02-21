package ndtq.repository;


import ndtq.model.Playlist;
import ndtq.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@Transactional
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findAllByOrderByViewsDesc();

    Iterable<Playlist> findAllByOrderByDateCreateDesc();

    Iterable<Playlist> findAllByUsers(Users users);

    Iterable<Playlist> findAllByNameContaining(String name);
    @Query(value = "select * from playlist where id in (select id_playlist from playlist_tag where id_tags in " +
                   "(select id from tags where tags.name = :name))", nativeQuery = true)
    Iterable<Playlist> findAllByTagsList(String name);

    @Query(value = "select * from playlist where id in (select id_playlist from playlist_tag where id_tags = :id)", nativeQuery = true)
    Iterable<Playlist> findPlaylistByTags( Long id);

    @Modifying
    @Query(value = "update Playlist set views=(views+ 1)", nativeQuery = true)
    void setViewsAllPlaylist();

    @Query (value = "select * from playlist order by views desc limit 10", nativeQuery = true)
    Iterable<Playlist> findTop10PlaylistsOrderByViewDesc();

    @Query(value = "select * from playlist order by last_update desc limit 10", nativeQuery = true)
    Iterable<Playlist> findTop10PlaylistOrderByDateDesc();
    @Modifying
    @Query(value = "DELETE FROM playlist_tag WHERE id_playlist = ?1", nativeQuery = true)
    void deletePlaylistInTag(Long idPlaylist);

    @Modifying
    @Query(value = "DELETE FROM like_user_playlist WHERE id_playlist = ?1", nativeQuery = true)
    void deletePlaylistInLike(Long idPlaylist);
    @Modifying
    @Query(value = "DELETE FROM comments WHERE id_playlist = ?1", nativeQuery = true)
    void deletePlaylistInComment(Long idPlaylist);

    @Modifying
    @Query(value = "DELETE FROM playlist_song WHERE id_playlist =  ?1", nativeQuery = true)
    void deletePlaylistInSongs(Long idPlaylist);

    @Query(value = "select playlist.id," +
            " playlist.avatar," +
            " playlist.date_create, playlist.description, playlist.last_update, playlist.name, playlist.views,playlist.id_users" +
            " from playlist" +
            " right join (select id_playlist, count(id_user) from like_user_playlist group by id_playlist order by count(id_user) desc limit 10)" +
            " as abc on playlist.id = abc.id_playlist;", nativeQuery = true)
    Iterable<Playlist> findAllTopLikePlaylist();
}
