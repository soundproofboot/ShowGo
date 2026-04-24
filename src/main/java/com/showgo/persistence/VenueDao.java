package com.showgo.persistence;

import com.showgo.entity.Venue;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * DAO for operations specific to venues
 */
public class VenueDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Venue> getVenuesByCityState(String city, String state) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Venue> query = builder.createQuery(Venue.class);
        Root<Venue> root = query.from(Venue.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get("city"), city),
                        builder.equal(root.get("state"), state)
                ));
        List<Venue> list = session.createQuery(query).getResultList();
        session.close();

        return list;
    }
}
