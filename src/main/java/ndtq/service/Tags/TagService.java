package ndtq.service.Tags;

import ndtq.model.Playlist;
import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.repository.IPlaylistRepository;
import ndtq.repository.ISongRepository;
import ndtq.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TagService implements ITagService {
    @Autowired
    public ITagRepository iTagRepository;
    @Autowired
    public ISongRepository iSongRepository;
    @Autowired
    public IPlaylistRepository iPlaylistRepository;

    @Override
    public Iterable<Tags> findAll() {
        return iTagRepository.findAll();
    }

    @Override
    public Optional<Tags> findById(Long id) {
        return iTagRepository.findById(id);
    }

    @Override
    public Tags save(Tags tags) {
        return iTagRepository.save(tags);
    }

    @Override
    public void remove(Long id) {
        iTagRepository.deleteById(id);
    }

    @Override
    public Iterable<Tags> StringToListObj(List<Tags> listTag) {
        List<Tags> list = new ArrayList<>();
        for (int i = 0; i < listTag.size(); i++) {
            if (!iTagRepository.findTagsByName(listTag.get(i).getName()).isPresent()) {
                save(new Tags(listTag.get(i).getName()));
            }
            list.add(iTagRepository.findTagsByName(listTag.get(i).getName()).get());
        }
        return list;
    }

    @Override
    public void saveListTag(List<String> listTag) {
        for (int i = 0; i < listTag.size(); i++) {
            if (!iTagRepository.findTagsByName(listTag.get(i)).isPresent()) {
                save(new Tags(listTag.get(i)));
            }
        }
    }

    @Override
    public void addSongTag(Long idSong, Long idTag) {
        if (iTagRepository.checkSongTag(idSong, idTag) == 0){
            iTagRepository.addSongTag(idSong, idTag);
        }

    }

    @Override
    public void addPlaylistTag(Long idPlaylist, Long idTag) {
        if (iTagRepository.checkPlaylistTag(idPlaylist, idTag) == 0) {
            iTagRepository.addPlaylistTag(idPlaylist, idTag);
        }
    }

    @Override
    public Iterable<BigInteger> findIdSongByTag(Long id) {
        return iTagRepository.findIdSongByTag(id);
    }

    @Override
    public Iterable<Songs> listSongByTag(Long id) {
        Iterable<BigInteger> listSongId = findIdSongByTag(id);
        List<Songs> songsList = new ArrayList<>();
        for (BigInteger i: listSongId) {
            Long songId = i.longValue();
            Songs songs = iSongRepository.findById(songId).get();
            songsList.add(songs);
        }
        return songsList;
    }

    @Override
    public Iterable<BigInteger> findIdPlaylistByTag(Long id) {
        return iTagRepository.findIdPlaylistByTag(id);
    }

    @Override
    public Iterable<Playlist> listPlaylistByTag(Long id) {
        Iterable<BigInteger> listPlaylistId = findIdPlaylistByTag(id);
        List<Playlist> listPlaylists = new ArrayList<>();
        for (BigInteger i: listPlaylistId) {
            Long playlistId = i.longValue();
            Playlist playlist = iPlaylistRepository.findById(playlistId).get();
            listPlaylists.add(playlist);
        }
        return listPlaylists;
    }

    @Override
    public Iterable<Tags> hint5Tags() {
        return iTagRepository.hint5Tags();
    }

    @Override
    public Iterable<Tags> get15Tag() {
        return iTagRepository.get15Tag();
    }
}
