package ndtq.repository;


import ndtq.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Iterable<Singer> findAllByNameContaining(String name);

    Optional<Singer> findByName(String name);
    @Modifying
    @Query(value = "INSERT INTO casestudy4.song_singer (id_song, id_singer) VALUES (?1, ?2);",nativeQuery = true)
    void addSingerSong(Long idSong, Long idSinger);
    @Query(value = "SELECT count(*) FROM casestudy4.song_singer where id_song=?1 and id_singer=?2" ,nativeQuery = true)
    Integer checkSongSinger(Long idSong, Long idSinger);
}
