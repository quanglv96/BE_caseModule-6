package ndtq.service.playlist;


import ndtq.model.Playlist;
import ndtq.model.Users;
import ndtq.service.IGeneralService;

public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> listTrending();
    Iterable<Playlist> listNewPlaylist();
    Iterable<Playlist> findAllByUsers(Users users);

    Iterable<Playlist> findAllByNameContaining(String name);

    Iterable<Playlist> findTopLikePlaylist();

}
