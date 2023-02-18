package ndtq.repository;


import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ISongRepository extends JpaRepository<Songs, Long> {
    Iterable<Songs> findAllByOrderByViewsDesc();

    Iterable<Songs> findAllByOrderByViewsAsc();

    Iterable<Songs> findAllByOrderByDateDesc();

    Iterable<Songs> findAllByUsers(Users users);

    Iterable<Songs> findAllByNameContaining(String name);

    Optional<Songs> findById(Long id);

    Optional<Songs> findByName(String name);



    @Modifying
    @Query(value = "update Songs set views=(views+ 1)", nativeQuery = true)
    void setViewsAllSong();

    @Modifying
    @Query(value = "select * from songs where id in (select id_song from song_singer  where id_singer= :id )", nativeQuery = true)
    Iterable<Songs> findAllBySingerList(Long id);
    @Modifying
    @Query(value="DELETE FROM dbmodule6.playlist_song WHERE id_songs = ?1", nativeQuery = true)
    void deleteSongInPlaylist(Long idSong);
    @Modifying
    @Query(value="DELETE FROM dbmodule6.song_tag WHERE id_song = ?1", nativeQuery = true)
    void deleteSongInTag(Long idSong);
    @Modifying
    @Query(value = "DELETE FROM dbmodule6.playlist_song WHERE id_playlist = ?1",nativeQuery = true)
    void deleteSongInSinger(Long idSong);
    @Modifying
    @Query(value ="DELETE FROM dbmodule6.like_user_song WHERE id_song= ?1", nativeQuery = true)
    void deleteSongsInLike(Long idSong);
    @Modifying
    @Query(value ="DELETE FROM dbmodule6.comments WHERE id_songs= ?1", nativeQuery = true)
    void deleteSongsInComment(Long idSong);

    @Query(value = "select * from dbmodule6.songs order by date desc limit 10", nativeQuery = true)
    Iterable<Songs> findTop10SongsOrderByDate();
    @Query(value = "SELECT * FROM dbmodule6.songs ORDER BY RAND() LIMIT 5",nativeQuery = true)
    Iterable<Songs> suggest5Songs();

    @Query(value = "select songs.id, songs.audio, songs.avatar, songs.composer, songs.date, songs.name, songs.views, songs.id_users from songs right join (select id_song, count(id_user) from like_user_song group by id_song order by count(id_user) desc limit 10) as view on songs.id = view.id_song", nativeQuery = true)
    Iterable<Songs> findTop10SongsOrderByLike();


}
