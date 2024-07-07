package org.application.musicalappication.repository;

import org.application.musicalappication.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserRepository {


    @Autowired
    private SessionFactory sessionFactory;

    public Client getClientByEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Client ");
        List<Client> clientList = query.list();
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getEmail().equals(email)){
                return clientList.get(i);
            }
        }
        return clientList.stream().filter(c -> c.getEmail().equals(email)).findFirst().get();
    }

    public Client getClientById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
