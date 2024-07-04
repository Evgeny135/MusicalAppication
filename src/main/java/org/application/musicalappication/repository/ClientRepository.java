package org.application.musicalappication.repository;

import jakarta.transaction.Transactional;
import org.application.musicalappication.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
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
}
