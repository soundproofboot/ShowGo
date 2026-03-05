package com.showgo.persistence;

import com.showgo.entity.Performer;
import com.showgo.entity.User;
import com.showgo.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserDaoTest {
    GenericDao<User> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. execute SQL which deletes everything from the table and inserts records
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(User.class);

        logger.debug("setup runs in UserDaoTest");
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Run cleandb.sql after all tests run to return test db to initial state
     */
    @AfterEach
    void tearDown() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getById() {
        User retrievedUser = dao.getById(1);
        assertEquals("user1", retrievedUser.getUsername());
    }

    @Test
    void getAll() {
        List<User> retrievedUsers = dao.getAll();
        assertEquals(3, retrievedUsers.size());
        assertEquals(dao.getById(1), retrievedUsers.get(0));
    }

    @Test
    void insertSuccess() {
        User testUser = new User("testUsername");
        int insertedId = dao.insert(testUser);
        assertNotEquals(0, insertedId);

        User insertedUser = dao.getById(insertedId);
        assertEquals(testUser, insertedUser);
    }

    @Test
    void updateSuccess() {
        String newUsername = "testUsername";
        User userToUpdate = dao.getById(1);
        userToUpdate.setUsername(newUsername);
        dao.update(userToUpdate);
        User retrievedUser = dao.getById(1);
        assertEquals(newUsername, retrievedUser.getUsername());
    }

    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void getByPropertyEqual() {
        List<User> users = dao.getByPropertyEqual("username", "user1");
        assertEquals(1, users.size());
        assertEquals(dao.getById(1), users.get(0));
    }

    @Test
    void getByPropertyLike() {
        List<User> users = dao.getByPropertyLike("username", "user");
        assertEquals(3, users.size());
    }

//    TODO follow a performer
//    user3 does not follow performer 3 in clean test db
    @Test
    void performerFollowSuccess() {
        User testUser = dao.getById(3);
        int initialPerformerFollowsSize = testUser.getPerformerFollows().size();

        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performerToFollow = performerDao.getById(3);

        testUser.addPerformerFollow(performerToFollow);
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int afterUpdatePerformerFollowsSize = userAfterUpdate.getPerformerFollows().size();

        assertEquals(initialPerformerFollowsSize + 1, afterUpdatePerformerFollowsSize);
    }
//    follow a venue
//    remove a performer follow
//    remove a venue follow
//    add event interest
//    remove event interest

}
