package ndtq.repository;


import ndtq.model.Songs;
import ndtq.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTML;
import java.math.BigInteger;
import java.util.Optional;

@Repository
@Transactional
public interface ITagRepository extends JpaRepository<Tags, Long> {
    Optional<Tags> findTagsByName(String name);

    @Modifying
    @Query(value = "INSERT INTO dbmodule6.song_tag (id_song, id_tags)VALUES (?1, ?2)", nativeQuery = true)
    void addSongTag(Long idSong,Long idTag);
    @Transactional
    @Modifying()
    @Query(value ="INSERT INTO dbmodule6.playlist_tag (id_playlist, id_tags) VALUES (?1, ?2)", nativeQuery = true)
    void addPlaylistTag(Long idPlaylist,Long idTag);

    @Query(value = "SELECT count(*) FROM dbmodule6.song_tag where id_song=?1 and id_tags=?2" ,nativeQuery = true)
    Integer checkSongTag(Long idSong,Long idTag);
    @Query(value = "SELECT count(*) FROM dbmodule6.playlist_tag where id_playlist=?1 and id_tags=?2" ,nativeQuery = true)
    Integer checkPlaylistTag(Long idPlaylist,Long idTag);
    @Query(value = "select song_tag.id_song from song_tag where id_tags = ?1", nativeQuery = true)
    Iterable<BigInteger> findIdSongByTag(Long id);
    @Query(value = "select playlist_tag.id_playlist from playlist_tag where id_tags = ?1", nativeQuery = true)
    Iterable<BigInteger> findIdPlaylistByTag(Long id);
    @Query(value = "SELECT * FROM tags ORDER BY RAND() LIMIT 5", nativeQuery = true)
    Iterable<Tags> hint5Tags();

    @Query(value = "select * from tags order by rand() limit 15", nativeQuery = true)
    Iterable<Tags> get15Tag();
}
