package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.PlaylistType;
import org.application.musicalappication.model.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Optional<List<Playlist>> getPlaylistByTrack(Long id){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Track.class, id).getPlaylists());
    }
    @Transactional
    public Optional<List<Playlist>> getAlbumsByUser(Long id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Playlist p WHERE (p.playlistType.name ='album' or p.playlistType.name ='Альбом') and p.client.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setParameter("id",id).getResultList());
    }

    @Transactional
    public Optional<List<Playlist>> getUserPlaylistsByUser(Long id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Playlist p WHERE (p.playlistType.name ='UserPlaylist' or p.playlistType.name ='Пользовательский плейлист') and p.client.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setParameter("id", id).getResultList());
    }

    @Transactional
    public Optional<Playlist> getPlaylistById(Long id){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Playlist.class, id));
    }
    @Transactional
    public void addPlaylist(Playlist playlist){
        Session session  = sessionFactory.getCurrentSession();
        session.persist(playlist);
    }

    @Transactional
    public Optional<List<Playlist>> getTopPlaylists(int maxCount){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Playlist ";

        return Optional.ofNullable(session.createQuery(hql, Playlist.class).setMaxResults(maxCount).getResultList());
    }

    @Transactional
    public Optional<List<Playlist>> findPlaylistsByTitle(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Playlist p WHERE lower(p.title) LIKE :str";

        List<Playlist> playlists = new ArrayList<>();

        // Равенство ключу
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(playlists);
    }

    @Transactional
    public Optional<List<Playlist>> findPlaylistsByClientName(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Playlist p WHERE lower(p.client.name) LIKE :str";

        List<Playlist> playlists = new ArrayList<>();

        // Равенство ключу
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(playlists);
    }

    @Transactional
    public Optional<List<Playlist>> findPlaylistsByDescription(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Playlist p WHERE lower(p.description) LIKE :str";

        List<Playlist> playlists = new ArrayList<>();

        // Равенство ключу
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(playlists);
    }

    @Transactional
    public Optional<List<Playlist>> findPlaylistsByTypeName(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Playlist p WHERE lower(p.playlistType.name) LIKE :str";

        List<Playlist> playlists = new ArrayList<>();

        // Равенство ключу
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        playlists.addAll(session.createQuery(hql, Playlist.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(playlists);
    }
}
