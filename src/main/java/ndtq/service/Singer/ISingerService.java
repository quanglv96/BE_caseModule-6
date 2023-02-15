package ndtq.service.Singer;



import ndtq.model.Singer;
import ndtq.service.IGeneralService;

import java.util.List;

public interface ISingerService extends IGeneralService<Singer> {
    Iterable<Singer> findAllByName(String name);
    void addSingerSong(Long idSong, Long idSinger);
    Iterable<Singer> StringToListObj(List<Singer> listSinger);
}
