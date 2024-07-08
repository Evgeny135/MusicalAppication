package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Optional<List<Track>> getTracksByUserId(Long id){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track t WHERE t.author.id = :id";

        return Optional.ofNullable(session.createQuery(hql, Track.class).setParameter("id",id).getResultList());
    }

    @Transactional
    public Optional<List<Track>> getTracksByPlaylist(Long id){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Track  t WHERE t.album = :id";

        return Optional.ofNullable(session.createQuery(hql, Track.class).setParameter("id", id).getResultList());
    }

    @Transactional
    public Optional<Track> getTrackById(Long id){
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(Track.class, id));
    }
}
