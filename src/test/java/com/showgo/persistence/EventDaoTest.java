package com.showgo.persistence;

import com.showgo.entity.Event;
import com.showgo.entity.Performer;
import com.showgo.entity.Venue;
import com.showgo.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventDaoTest {
    GenericDao<Event> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Event.class);
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
        Event retrievedEvent = (Event) dao.getById(1);
        assertEquals("event1", retrievedEvent.getTitle());
    }

    @Test
    void getAll() {
        List<Event> retrievedEvents = dao.getAll();
        assertEquals(3, retrievedEvents.size());
        assertEquals(dao.getById(1), retrievedEvents.get(0));
    }

    @Test
    void insertSuccess() {
//        get a venue
        GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
        Venue venue = venueDao.getById(1);

        Event testEvent = new Event("testEvent", venue);

//        insert testEvent
        int insertedEventId = dao.insert(testEvent);

//        retrieve insertedOrder
        Event retrievedEvent = dao.getById(insertedEventId);

        assertNotNull(retrievedEvent);
        assertEquals(testEvent.getTitle(), retrievedEvent.getTitle());
        assertEquals("venue1", testEvent.getVenue().getName());
    }

    @Test
    void updateSuccess() {
        String newEventTitle = "testEventTitle";
        Event eventToUpdate = (Event) dao.getById(1);
        eventToUpdate.setTitle(newEventTitle);
        dao.update(eventToUpdate);
        Event retrievedEvent = dao.getById(1);
        assertEquals(newEventTitle, retrievedEvent.getTitle());
    }

    @Test
    void deleteSuccess() {
        dao.delete((Event) dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void getByPropertyEqual() {
        List<Event> events = dao.getByPropertyEqual("title", "event1");
        assertEquals(1, events.size());
        assertEquals(dao.getById(1), events.get(0));
    }

    @Test
    void getByPropertyLike() {
        List<Event> events = dao.getByPropertyLike("title", "event");
        assertEquals(3, events.size());
    }

    @Test
    void addPerformerToEvent() {
//        event3 does not have performer1 in lineup
        Event testEvent = dao.getById(3);
        int initialEventLineupSize = testEvent.getPerformers().size();

        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performerToAdd = performerDao.getById(1);

        testEvent.addEventPerformer(performerToAdd);
        dao.update(testEvent);

        Event eventAfterUpdate = dao.getById(3);
        int lineupSizeAfterUpdate = eventAfterUpdate.getPerformers().size();

        assertEquals(initialEventLineupSize + 1, lineupSizeAfterUpdate);
    }

    @Test
    void removePerformerFromEvent() {
        Event testEvent = dao.getById(1);
        int initialEventLineupSize = testEvent.getPerformers().size();

        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performerToRemove = performerDao.getById(1);

        testEvent.removeEventPerformer(performerToRemove);
        dao.update(testEvent);

        Event eventAfterUpdate = dao.getById(1);
        int lineupSizeAfterUpdate = eventAfterUpdate.getPerformers().size();

        assertEquals(initialEventLineupSize - 1, lineupSizeAfterUpdate);
    }
}
