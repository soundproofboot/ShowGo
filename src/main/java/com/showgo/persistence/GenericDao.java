package com.showgo.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * A generic DAO
 */

public class GenericDao<T extends Identifiable> {
    private final Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiate a new Generic dao
     *
     * @param type the entity type, ex. User
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Get entity by id
     * @param id entity id
     * @return an instance of the entity
     */
    public <T> T getById(int id) {
        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Gets all entities
     *
     * @return all entities
     */
    public List<T> getAll() {
        Session session = getSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Insert new entity
     * @param entity entity to insert
     */
    public int insert(T entity) {
        int id;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        id = entity.getId();
        session.close();
        return id;
    }

    /**
     * Update entity
     * @param entity entity to be updated
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Delete entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get entity by property (exact match)
     *
     * @param propertyName column to search by
     * @param value value to match
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Get entity by property like
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createQuery(query).getResultList();
        session.close();

        return list;
    }
}
