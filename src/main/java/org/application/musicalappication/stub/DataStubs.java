package org.application.musicalappication.stub;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;

import java.sql.Time;
import java.util.List;

public class DataStubs {
    ClientStub client;
    public class ClientStub{
        String username;
        ClientStub(String name){
            username = name;
        }
    }
    public class TrackStub{
        String title;
        String album;
        Time length;
    }

    public class PlaylistStub{
        String title;
        String type;
        int size;
    }
}
