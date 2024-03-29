package ndtq.service.Songs;


import ndtq.model.Singer;
import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.model.Users;
import ndtq.repository.ISongRepository;
import ndtq.service.Singer.ISingerService;
import ndtq.service.Tags.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository isongRepository;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ISingerService singerService;

    @Override
    public Iterable<Songs> findAll() {
        isongRepository.setViewsAllSong();
        return isongRepository.findAll();
    }
    @Override
    public Iterable<Songs> listTrending() {
        return isongRepository.findAllByOrderByViewsDesc();
    }
    @Override
    public Iterable<Songs> listTrendingAsc() {
        return isongRepository.findAllByOrderByViewsAsc();
    }

    @Override
    public Iterable<Songs> listTop10SongsTrending() {
        return isongRepository.findTop10SongsOrderByDate();
    }
    @Override
    public Iterable<Songs> listTop10SongsLikeTrending() {
        return isongRepository.findTop10SongsOrderByLike();
    }

    @Override
    public Iterable<Songs> findAllByNameContaining(String name) {
        return isongRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Songs> listNewSongs() {
        return isongRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Iterable<Songs> findAllByUsers(Users users) {
        return isongRepository.findAllByUsers(users);
    }


    @Override
    public Optional<Songs> findById(Long id) {
        return isongRepository.findById(id);
    }

    @Override
    public Songs save(Songs songs) {
        List<Tags> tagsList = (List<Tags>) tagService.StringToListObj(songs.getTagsList());
        songs.setTagsList(tagsList);
        List<Singer> singerList= (List<Singer>) singerService.StringToListObj(songs.getSingerList());
        songs.setSingerList(singerList);
        songs=isongRepository.save(songs);
        return songs;
    }

    @Override
    public void remove(Long id) {
        isongRepository.deleteSongInPlaylist(id);
        isongRepository.deleteSongInTag(id);
        isongRepository.deleteSongInSinger(id);
        isongRepository.deleteSongsInLike(id);
        isongRepository.deleteSongsInComment(id);
        isongRepository.deleteById(id);
    }

    @Override
    public Iterable<Songs> findAllBySingerList(Long id) {
        return isongRepository.findAllBySingerList(id);
    }

    @Override
    public  Iterable<Songs> suggest5Songs(Long idSongNow) {
        return isongRepository.hint5Songs(idSongNow);
    }

    @Override
    public void changeLike(Songs songs) {
        isongRepository.save(songs);
    }

    @Override
    public Iterable<Songs> getAllSongByTag(Long id) {
        return isongRepository.getAllSongByTag(id);
    }
}
