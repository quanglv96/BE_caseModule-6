package ndtq.service.Tags;

import ndtq.model.Tags;
import ndtq.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {
    @Autowired
    public ITagRepository iTagRepository;

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

    //TODO hạn chế lồng vòng for rồi sử dụng các query xuống tầng database lý do khi có nhiều dữ liệu vòng for chạy quá nhiều thì số lần chọc xuống db sẽ làm quá tải db.
    // giải pháp: sử dụng query 1 lần cho logic như thế này bằng cách dùng câu lệnh điều kiện in.
    // ex: findTagsByNameIn(List<String> listTag)
    @Override
    public Iterable<Tags> StringToListObj(List<String> listTag) {
        List<Tags> list = new ArrayList<>();
        for (int i = 1; i < listTag.size(); i++) {
            if (!iTagRepository.findTagsByName(listTag.get(i)).isPresent()) {
                save(new Tags(listTag.get(i)));
            }
            list.add(iTagRepository.findTagsByName(listTag.get(i)).get());
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

}
