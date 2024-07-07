package org.application.musicalappication.repository;

import org.application.musicalappication.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Role> getRoleByName(String name){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Role WHERE name = :name";
        return Optional.ofNullable(session.createQuery(hql, Role.class).setParameter("name", name).uniqueResult());
    }

}
