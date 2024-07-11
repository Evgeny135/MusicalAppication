package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.PlaylistType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistTypeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PlaylistTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Optional<List<PlaylistType>> getAllTrackType(){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM PlaylistType";

        return Optional.ofNullable(session.createQuery(hql, PlaylistType.class).getResultList());
    }
}
