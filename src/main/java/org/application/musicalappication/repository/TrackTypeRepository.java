package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.TrackType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrackTypeRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TrackTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Optional<List<TrackType>> getAllTrackType(){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM TrackType";

        return Optional.ofNullable(session.createQuery(hql, TrackType.class).getResultList());
    }
}
