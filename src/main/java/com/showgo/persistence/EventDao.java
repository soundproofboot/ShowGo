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

    /**
     * Get events only at venues in provided city and state
     * @param city the city
     * @param state the state
     * @return list of events in city/state
     */
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
        query.orderBy(builder.asc(root.get("eventStart")));

        List<Event> events = session.createQuery(query).getResultList();
        session.close();

        return events;
    }

    /**
     * Get n number of events ordered by most recent creation date
     * @param numResults number of events to return
     * @return list of n events ordered by creation date desc
     */
    public List<Event> getMostRecentEvents(int numResults) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.orderBy(builder.desc(root.get("createdAt")));

        List<Event> recentEvents = session.createQuery(query)
                .setMaxResults(numResults)
                .getResultList();

        session.close();
        return recentEvents;
    }
}
