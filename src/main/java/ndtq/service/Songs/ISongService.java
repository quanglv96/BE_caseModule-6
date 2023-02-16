package ndtq.service.Songs;



import ndtq.model.Songs;
import ndtq.model.Users;
import ndtq.service.IGeneralService;

import java.util.List;

public interface ISongService extends IGeneralService<Songs> {
    Iterable<Songs> listTrending();
    Iterable<Songs> listTrendingAsc();
    Iterable<Songs> listTop10SongsTrending();
    Iterable<Songs> listTop10SongsLikeTrending();
    Iterable<Songs> findAllByNameContaining(String name);
    Iterable<Songs> listNewSongs();
    Iterable<Songs> findAllByUsers(Users users);
    Iterable<Songs> findAllBySingerList(Long id);
    Iterable<Songs> suggest5Songs();
}
