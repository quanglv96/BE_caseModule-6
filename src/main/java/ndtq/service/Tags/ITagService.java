package ndtq.service.Tags;


import ndtq.model.Tags;
import ndtq.service.IGeneralService;

import java.util.List;

public interface ITagService extends IGeneralService<Tags> {
    Iterable<Tags> StringToListObj(List<String> listTag);
    void saveListTag(List<String> listTag);
    void addSongTag(Long idSong, Long idTag);
    void addPlaylistTag(Long idPlaylist,Long idTag);

}
