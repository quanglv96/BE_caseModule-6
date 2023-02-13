package ndtq.service.Singer;


import ndtq.model.Singer;
import ndtq.repository.ISingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@Transactional
public class SingerService implements ISingerService{
    @Autowired
    private ISingerRepository singerRepository;


    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void remove(Long id) {
        singerRepository.deleteById(id);
    }
    @Override
    public void addSingerSong(Long idSong, Long idSinger){
        if (singerRepository.checkSongSinger(idSong, idSinger) == 0){
            singerRepository.addSingerSong(idSong,idSinger);
        }

    }

    @Override
    public Iterable<Singer> findAllByName(String name) {

        return singerRepository.findAllByNameContaining(name);
    }
    @Override
    public Iterable<Singer> StringToListObj(List<String> listSinger) {
        List<Singer> list = new ArrayList<>();
        for (int i = 0; i < listSinger.size(); i++) {
            if(!singerRepository.findByName(listSinger.get(i)).isPresent() && !Objects.equals(listSinger.get(i), "")){
                save(new Singer(listSinger.get(i)));
            }
            list.add(singerRepository.findByName(listSinger.get(i)).get());
        }
        return list;
    }
}
