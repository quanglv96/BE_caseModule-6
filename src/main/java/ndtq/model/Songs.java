package ndtq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String audio;
    private String avatar;
    @NotNull
    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "id_users")
    private Users users;
    @ManyToMany(targetEntity = Singer.class)
    @JoinTable(name = "song_singer",joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_singer")})
    private List<Singer> singerList;
    private String composer; // người sáng tác
    private LocalDate date;
    @ManyToMany(targetEntity = Tags.class)
    @JoinTable( name = "song_tag",joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_tags")})
    private List<Tags> tagsList;
    private Long views;
    @ManyToMany(targetEntity = Users.class)
    @JoinTable( name = "like_user_song",joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<Users> userLikeSong;

    public Songs(String name, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList) {
        this.name = name;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
        this.tagsList = tagsList;

    }

    public Songs(Long id, String name, String audio, String avatar, Users users, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList, Long views, List<Users> userLikeSong) {
        this.id = id;
        this.name = name;
        this.audio = audio;
        this.avatar = avatar;
        this.users = users;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
        this.tagsList = tagsList;
        this.views = views;
        this.userLikeSong = userLikeSong;
    }

    public Songs() {
    }

    public Songs(String name, String audio, String avatar, Users users, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList, Long views, List<Users> userLikeSong) {
        this.name = name;
        this.audio = audio;
        this.avatar = avatar;
        this.users = users;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
                this.tagsList = tagsList;
        this.views = views;
        this.userLikeSong = userLikeSong;
    }

}
