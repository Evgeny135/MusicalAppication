package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Optional<Client> getClientByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Client WHERE email = :email";

        return  Optional.ofNullable(session.createQuery(hql, Client.class)
                .setParameter("email", email)
                .uniqueResult());
    }

    @Transactional
    public Optional<Client> getClientById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return  Optional.ofNullable(session.find(Client.class,id));
    }
    @Transactional
    public void register(Client client){
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }
}
