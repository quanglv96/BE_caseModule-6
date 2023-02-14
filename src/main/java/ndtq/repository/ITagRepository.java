package ndtq.repository;


import ndtq.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ITagRepository extends JpaRepository<Tags, Long> {
    Optional<Tags> findTagsByName(String name);

    @Modifying
    @Query(value = "INSERT INTO casestudy4.song_tag (id_song, id_tags)VALUES (?1, ?2)", nativeQuery = true)
    void addSongTag(Long idSong,Long idTag);
    @Transactional
    @Modifying()
    @Query(value ="INSERT INTO dbmodule6.playlist_tag (id_playlist, id_tags) VALUES (?1, ?2)", nativeQuery = true)
    void addPlaylistTag(Long idPlaylist,Long idTag);

    @Query(value = "SELECT count(*) FROM dbmodule6.song_tag where id_song=?1 and id_tags=?2" ,nativeQuery = true)
    Integer checkSongTag(Long idSong,Long idTag);
    @Query(value = "SELECT count(*) FROM dbmodule6.playlist_tag where id_playlist=?1 and id_tags=?2" ,nativeQuery = true)
    Integer checkPlaylistTag(Long idPlaylist,Long idTag);
}
