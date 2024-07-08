package org.application.musicalappication.stub;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class DataStubs {
    public ClientStub client;
    public List<PlaylistStub> playlists;
    public List<PlaylistStub> albums;
    public List<TrackStub> tracks;

    public DataStubs() {
        this.client = new ClientStub("Шурик");
        this.playlists = new LinkedList<>();
        this.playlists.add(new PlaylistStub("Для сна", "Пользовательский плейлист",3));
        this.playlists.add(new PlaylistStub("Качалка", "Пользовательский плейлист",666));
        this.playlists.add(new PlaylistStub("TRUE CVLT", "Пользовательский плейлист",0));
        this.playlists.add(new PlaylistStub("Постпанк", "Пользовательский плейлист",100));
        this.albums = new LinkedList<>();
        this.albums.add(new PlaylistStub("Ночная смена", "EP",4));
        this.albums.add(new PlaylistStub("Орут хаты", "Сингл",1));
        this.albums.add(new PlaylistStub("Все что я хочу", "Альбом",9));
        this.tracks = new LinkedList<>();
        this.tracks.add(new TrackStub("Работа 2х2","Ночная смена",Time.valueOf("00:3:15")));
        this.tracks.add(new TrackStub("День курка","Ночная смена",Time.valueOf("00:4:10")));
        this.tracks.add(new TrackStub("Добрый день","Все что я хочу",Time.valueOf("00:3:15")));
        this.tracks.add(new TrackStub("Под гитару","Все что я хочу",Time.valueOf("00:3:22")));
        this.tracks.add(new TrackStub("Большие города","Все что я хочу",Time.valueOf("00:3:15")));
        this.tracks.add(new TrackStub("Холодная вода","Орут хаты",Time.valueOf("00:5:05")));
    }

    public class ClientStub{
        public String  name;

        public String getName() {
            return name;
        }

        public ClientStub(String name){
            this.name = name;
        }
    }
    public class TrackStub{
        String title;
        String album;
        Time length;

        public String getTitle() {
            return title;
        }

        public String getAlbum() {
            return album;
        }

        public Time getLength() {
            return length;
        }

        public TrackStub(String title, String album, Time length) {
            this.title = title;
            this.album = album;
            this.length = length;
        }
    }

    public class PlaylistStub{
        String title;
        String playlistType;
        int size;

        public String getTitle() {
            return title;
        }

        public String getPlaylistType() {
            return playlistType;
        }

        public int getSize() {
            return size;
        }

        public PlaylistStub(String title, String type, int size) {
            this.title = title;
            this.playlistType = type;
            this.size = size;
        }
    }
}
