package ndtq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data

public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description; // mô tả nội dung bài hát
    private String avatar;
    private LocalDate dateCreate; // ngày tạo
    private LocalDate lastUpdate;
    // ngày cập nhập lần cuối

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "id_users")
    private Users users;// ngươi tạo playlist
    @ManyToMany(targetEntity = Songs.class)
    @JoinTable(name = "playlist_song", joinColumns = {@JoinColumn(name = "id_playlist")},
            inverseJoinColumns = {@JoinColumn(name = "id_songs")})
    private List<Songs> songsList;
    @ManyToMany(targetEntity = Tags.class)
    @JoinTable(name = "playlist_tag",
            joinColumns = {@JoinColumn(name = "id_playlist")},
            inverseJoinColumns = {@JoinColumn(name = "id_tags")})
    private List<Tags> tagsList;
    private long views;
    @ManyToMany(targetEntity = Users.class)
    @JoinTable(name = "like_user_playlist", joinColumns = {@JoinColumn(name = "id_playlist")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<Users> userLikesPlaylist;

    public Playlist(String name, String description, LocalDate dateCreate, LocalDate lastUpdate, Users users, List<Songs> songsList, List<Tags> tagsList, long views, List<Users> userLikesPlaylist) {
        this.name = name;
        this.description = description;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.songsList = songsList;
        this.tagsList = tagsList;
        this.views = views;
        this.userLikesPlaylist = userLikesPlaylist;
    }

    public Playlist(String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
    }

    public Playlist(String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users, List<Tags> tagsList, long views, List<Users> userLikesPlaylist) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.tagsList = tagsList;
        this.views = views;
        this.userLikesPlaylist = userLikesPlaylist;
    }

    public Playlist(String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users, long views, List<Users> userLikesPlaylist) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.views = views;
        this.userLikesPlaylist = userLikesPlaylist;
    }

    public Playlist(Long id, String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users, List<Songs> songsList, List<Tags> tagsList, long views, List<Users> userLikesPlaylist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.songsList = songsList;
        this.tagsList = tagsList;
        this.views = views;
        this.userLikesPlaylist = userLikesPlaylist;
    }

    public Playlist(String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users, List<Songs> songsList, List<Tags> tagsList, long views, List<Users> userLikesPlaylist) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.songsList = songsList;
        this.tagsList = tagsList;
        this.views = views;
        this.userLikesPlaylist = userLikesPlaylist;
    }

    public Playlist() {
    }

    public Playlist(String name, String description, String avatar, LocalDate dateCreate, LocalDate lastUpdate, Users users, List<Tags> tagsList, long views) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.tagsList = tagsList;
        this.views = views;

    }
}

