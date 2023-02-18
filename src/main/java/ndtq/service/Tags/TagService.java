package ndtq.service.Tags;

import ndtq.model.Songs;
import ndtq.model.Tags;
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
        Iterable<BigInteger> listSongId = iTagRepository.findIdSongByTag(id);
        List<Songs> songsList = new ArrayList<>();
        for (BigInteger i: listSongId) {
            Long songId = i.longValue();
            Songs songs = iSongRepository.findById(songId).get();
            songsList.add(songs);
        }
        return songsList;
    }
}
