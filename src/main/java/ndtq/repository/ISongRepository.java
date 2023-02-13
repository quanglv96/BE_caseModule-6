package ndtq.repository;


import ndtq.model.Songs;
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
    @Query(value="DELETE FROM casestudy4.playlist_song WHERE id_songs = ?1", nativeQuery = true)
    void deleteSongInPlaylist(Long idSong);
    @Modifying
    @Query(value="DELETE FROM casestudy4.song_tag WHERE id_song = ?1", nativeQuery = true)
    void deleteSongInTag(Long idSong);
    @Modifying
    @Query(value = "DELETE FROM casestudy4.playlist_song WHERE id_playlist = ?1",nativeQuery = true)
    void deleteSongInSinger(Long idSong);

    @Query(value = "select * from songs order by date desc limit 10", nativeQuery = true)
    Iterable<Songs> findTop10SongsOrderByDate();

}
