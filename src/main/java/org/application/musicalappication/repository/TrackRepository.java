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
public class TrackRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TrackRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addNewTrack(Track track){
        Session session  = sessionFactory.getCurrentSession();
        
        session.persist(track);
    }

    @Transactional
    public Optional<List<Track>> getTracksByUserId(Long id){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE t.author.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Track.class).setParameter("id",id).getResultList());
    }

    @Transactional
    public Optional<List<Track>> getTracksByPlaylist(Long id){
        Session session = sessionFactory.getCurrentSession();

        List<Track> listTrack = session.get(Playlist.class, id).getTracks();

        return Optional.ofNullable(listTrack);
    }

    @Transactional
    public Optional<Track> getTrackById(Long id){
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(Track.class, id));
    }

    @Transactional
    public Optional<List<Track>> getTop100Tracks(){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track";

        return Optional.ofNullable(session.createQuery(hql, Track.class).setMaxResults(100).getResultList());
    }

    @Transactional
    public Optional<List<Track>> findTracksByTitle(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE lower(t.title) LIKE :str";

        List<Track> trackList = new ArrayList<>();

        // Равенство ключу
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(trackList);
    }

    @Transactional
    public Optional<List<Track>> findTracksByClientName(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE lower(t.author.name) LIKE :str";

        List<Track> trackList = new ArrayList<>();

        // Равенство ключу
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(trackList);
    }

    @Transactional
    public Optional<List<Track>> findTracksByAlbum(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE lower(t.album) LIKE :str";

        List<Track> trackList = new ArrayList<>();

        // Равенство ключу
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(trackList);
    }

    @Transactional
    public Optional<List<Track>> findTracksByTypeName(String key){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE lower(t.trackType.name) LIKE :str";

        List<Track> trackList = new ArrayList<>();

        // Равенство ключу
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str", key).getResultList());
        // Начинается с ключа
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str",key + "_%").getResultList());
        // Содержит подстроку
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key + "_%").getResultList());
        // Оканчивается на ключ
        trackList.addAll(session.createQuery(hql, Track.class).setParameter("str","%_" + key).getResultList());

        return Optional.of(trackList);
    }
}
