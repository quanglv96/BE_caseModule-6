package ndtq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String content;
    @NotNull
    @OneToOne
    @JoinColumn(name="id_users")
    private Users users;
    @OneToOne
    @JoinColumn(name="id_songs")
    private Songs songs;
    @OneToOne
    @JoinColumn(name="id_playlist")
    private Playlist playlist;

    public Comments(LocalDateTime date, String content, Users users, Songs songs, Playlist playlist) {
        this.date = date;
        this.content = content;
        this.users = users;
        this.songs = songs;
        this.playlist = playlist;
    }

    public Comments(String content, Users users, Playlist playlist) {
        this.content = content;
        this.users = users;
        this.playlist = playlist;
    }

    public Comments(String content, Users users, Songs songs) {
        this.content = content;
        this.users = users;
        this.songs = songs;
    }

    public Comments() {
    }
}
