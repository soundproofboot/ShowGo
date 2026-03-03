package com.showgo.persistence;

/**
 * Implemented by all entities to allow getId to be
 * called in GenericDao
 *
 * @see GenericDao
 */
public interface Identifiable {
    int getId();
}
