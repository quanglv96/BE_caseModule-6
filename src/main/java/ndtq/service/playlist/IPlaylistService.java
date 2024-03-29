package ndtq.service.playlist;


import ndtq.model.Playlist;
import ndtq.model.Users;
import ndtq.service.IGeneralService;

public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> listTrending();
    Iterable<Playlist> listNewPlaylist();

    Iterable<Playlist> listTop10ViewsPlaylistTrending();
    Iterable<Playlist> listTop10PlaylistOrderByDateDesc();

    Iterable<Playlist> findAllByUsers(Users users);
    Iterable<Playlist> findAllByNameContaining(String name);

    Iterable<Playlist> findTopLikePlaylist();

    Iterable<Playlist> findAllByTaglists(String name);

    Iterable<Playlist> findPlaylistByTags(Long id);

}
