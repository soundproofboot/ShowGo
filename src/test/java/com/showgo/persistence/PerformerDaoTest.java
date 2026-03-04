package com.showgo.persistence;

import com.showgo.entity.Performer;
import com.showgo.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PerformerDaoTest {
    GenericDao<Performer> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Performer.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getById() {
        Performer retrievedPerformer = (Performer) dao.getById(1);
        logger.debug(retrievedPerformer);
        assertEquals("performer1", retrievedPerformer.getName());
    }

    @Test
    void getAll() {
        List<Performer> retrievedPerformers = dao.getAll();
        assertEquals(3, retrievedPerformers.size());
        assertEquals(dao.getById(1), retrievedPerformers.get(0));
    }

    @Test
    void insertSuccess() {
        Performer testPerformer = new Performer("testPerformerName");
        int insertedId = dao.insert(testPerformer);
        assertNotEquals(0, insertedId);

        Performer insertedPerformer = dao.getById(insertedId);
        assertEquals(testPerformer, insertedPerformer);
    }

    @Test
    void updateSuccess() {
        String newPerformerName = "testPerformer";
        Performer performerToUpdate = (Performer) dao.getById(1);
        performerToUpdate.setName(newPerformerName);
        dao.update(performerToUpdate);

        Performer retrievedPerformer = (Performer) dao.getById(1);
        assertEquals(newPerformerName, retrievedPerformer.getName());
    }

    @Test
    void deleteSuccess() {
        dao.delete((Performer) dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void getByPropertyEqual() {
        List<Performer> performers = dao.getByPropertyEqual("name", "performer1");
        assertEquals(1, performers.size());
        assertEquals(dao.getById(1), performers.get(0));
    }
}
