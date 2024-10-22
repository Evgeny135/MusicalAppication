package org.application.musicalappication.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
public class Track {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Client author;

    @Column(name = "album")
    private String album;

    @Column(name = "cover")
    private String cover;

    @Column(name = "length")
    private Time length;

    @ManyToOne
    @JoinColumn(name = "track_type", referencedColumnName = "id")
    private TrackType trackType;

    @Column(name = "tags")
    private String tags;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists;

    public Track() {
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
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

    public Client getAuthor() {
        return author;
    }

    public void setAuthor(Client author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackType trackType) {
        this.trackType = trackType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", album=" + album +
                ", cover='" + cover + '\'' +
                ", length='" + length + '\'' +
                ", trackType=" + trackType +
                ", tags='" + tags + '\'' +
                '}';
    }
}
