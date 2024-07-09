package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.PlaylistType;
import org.application.musicalappication.model.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository {

    private final SessionFactory sessionFactory;
    @Autowired
    public PlaylistRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Optional<List<Playlist>> getPlaylistByClient(Long id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Playlist p WHERE p.client.id = :client";
        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setParameter("client",id).getResultList());
    }

    @Transactional
    public Optional<List<Playlist>> getAlbumsByUser(Long id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Playlist p WHERE p.playlistType.name ='album' and p.client.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setParameter("id",id).getResultList());
    }

    @Transactional
    public Optional<List<Playlist>> getUserPlaylistsByUser(Long id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Playlist p WHERE p.playlistType.name ='UserPlaylist' and p.client.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setParameter("id", id).getResultList());
    }

    @Transactional
    public Optional<Playlist> getPlaylistById(Long id){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Playlist.class, id));
    }

    @Transactional
    public Optional<List<Playlist>> getPlaylistByTrack(Long id){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Track.class, id).getPlaylists());
    }

}
