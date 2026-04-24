package com.showgo.persistence;

import com.showgo.entity.Event;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * DAO for operations specific to Events
 */
public class EventDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Event> getEventsByCityState(String city, String state) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get("venue").get("city"), city),
                        builder.equal(root.get("venue").get("state"), state)
                ));
        List<Event> events = session.createQuery(query).getResultList();
        session.close();

        return events;
    }
}
