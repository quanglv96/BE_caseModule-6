package ndtq.service.Tags;


import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.service.IGeneralService;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ITagService extends IGeneralService<Tags> {
    Iterable<Tags> StringToListObj(List<Tags> listTag);
    void saveListTag(List<String> listTag);
    void addSongTag(Long idSong, Long idTag);
    void addPlaylistTag(Long idPlaylist,Long idTag);
    Iterable<BigInteger> findIdSongByTag(Long id);
    Iterable<Songs> listSongByTag(Long id);
    Iterable<BigInteger> findIdPlaylistByTag(Long id);
    Iterable<Playlist> listPlaylistByTag(Long id);
    Iterable<Tags> hint5Tags();


}
