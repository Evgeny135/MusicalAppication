package org.application.musicalappication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Client client;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "playlist_type", referencedColumnName = "id")
    private PlaylistType playlistType;

    @Column(name = "cover")
    private String cover;

    public Playlist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @ManyToMany
    @JoinTable(
            name = "tracking_playlist",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Track> tracks;

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", client=" + client +
                ", description='" + description + '\'' +
                ", playlistType=" + playlistType +
                ", cover='" + cover + '\'' +
                '}';
    }
}
