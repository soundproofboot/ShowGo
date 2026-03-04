package com.showgo.persistence;

import com.showgo.entity.User;
import com.showgo.entity.Venue;
import com.showgo.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VenueDaoTest {
    GenericDao<Venue> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Venue.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Run cleandb.sql after all tests run to return testdb to initial state
     */
    @AfterEach
    void tearDown() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getById() {
        Venue retrievedVenue = (Venue) dao.getById(1);
        assertEquals("venue1", retrievedVenue.getName());
    }

    @Test
    void getAll() {
        List<Venue> retrievedVenues = dao.getAll();
        assertEquals(3, retrievedVenues.size());
        assertEquals(dao.getById(1), retrievedVenues.get(0));
    }

    @Test
    void insertSuccess() {
        Venue testVenue = new Venue("testVenue");
        int insertedId = dao.insert(testVenue);
        assertNotEquals(0, insertedId);

        Venue insertedVenue = dao.getById(insertedId);
        assertEquals(testVenue, insertedVenue);
    }

    @Test
    void updateSuccess() {
        String newVenueName = "testVenueName";
        Venue venueToUpdate = (Venue) dao.getById(1);
        venueToUpdate.setName(newVenueName);
        dao.update(venueToUpdate);
        Venue retrievedVenue = (Venue) dao.getById(1);
        assertEquals(newVenueName, retrievedVenue.getName());
    }

    @Test
    void deleteSuccess() {
        dao.delete((Venue) dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void getByPropertyEqual() {
        List<Venue> venues = dao.getByPropertyEqual("name", "venue1");
        assertEquals(1, venues.size());
        assertEquals(dao.getById(1), venues.get(0));
    }

    @Test
    void getByPropertyLike() {
        List<Venue> venues = dao.getByPropertyLike("name", "venue");
        assertEquals(3, venues.size());
    }
}
