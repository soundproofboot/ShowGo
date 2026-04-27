package com.showgo.persistence;

import com.showgo.entity.*;
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
        logger.debug("setUp for UserDaoTest");
        dao = new GenericDao<>(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Run cleandb.sql after all tests run to return test db to initial state
     */
    @AfterEach
    void tearDown() {
        logger.debug("tearDown for UserDaoTest");
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
        logger.debug("insertSuccess");
        User testUser = new User("testUsername", "testCognitoId", "testUser@email.com", "testCity", "AA");
        int insertedId = dao.insert(testUser);
        assertNotEquals(0, insertedId);

        User insertedUser = dao.getById(insertedId);
        assertEquals(testUser, insertedUser);
    }

    @Test
    void createFromCognito() {
//        create with only email and cognitoId returned from aws
        User newUser = new User(null, "abc123", "email@email.com", null, null);
        int insertedId = dao.insert(newUser);
        assertNotEquals(0, insertedId);

        User insertedUser = dao.getById(insertedId);
        assertEquals(newUser, insertedUser);
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

    //    user3 does not follow performer 3 in clean test db
    @Test
    void performerFollowSuccess() {
        User testUser = dao.getById(3);
        int initialPerformerFollowSize = testUser.getPerformerFollows().size();

        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performerToFollow = performerDao.getById(3);

        testUser.addPerformerFollow(performerToFollow);
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int afterUpdatePerformerFollowsSize = userAfterUpdate.getPerformerFollows().size();

        assertEquals(initialPerformerFollowSize + 1, afterUpdatePerformerFollowsSize);
    }

//    user1 follows all performers in clean db so can remove performer1
    @Test
    void performerUnfollowSuccess() {
        User testUser = dao.getById(1);
        int initialPerformerFollowSize = testUser.getPerformerFollows().size();

        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performerToUnfollow = performerDao.getById(1);

        testUser.getPerformerFollows().forEach(pf -> {
            System.out.println("PerformerFollow id=" + pf.getId()
                    + " performer instance hashCode=" + System.identityHashCode(pf.getPerformer())
                    + " performer name=" + pf.getPerformer().getName());
        });
        System.out.println("performerToUnfollow instance hashCode=" + System.identityHashCode(performerToUnfollow));

        testUser.removePerformerFollow(performerToUnfollow);
//        System.out.println("performerFollows size after remove: " + testUser.getPerformerFollows().size());
//        System.out.println("performer followers size after remove: " + performerToUnfollow.getFollowers().size());
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int afterUpdatePerformerFollowsSize = userAfterUpdate.getPerformerFollows().size();

        assertEquals(initialPerformerFollowSize - 1, afterUpdatePerformerFollowsSize);
    }

    //    user3 does not follow venue 3 in clean test db
    @Test
    void venueFollowSuccess() {
        User testUser = dao.getById(3);
        int initialVenueFollowsSize = testUser.getVenueFollows().size();

        GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
        Venue venueToFollow = venueDao.getById(3);

        testUser.addVenueFollow(venueToFollow);
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int afterUpdateVenueFollowsSize = userAfterUpdate.getVenueFollows().size();

        assertEquals(initialVenueFollowsSize + 1, afterUpdateVenueFollowsSize);
    }

    //    user1 follows all venues in clean db so can remove venue1
    @Test
    void venueUnfollowSuccess() {
        User testUser = dao.getById(1);
        int initialVenueFollowsSize = testUser.getVenueFollows().size();

        Venue venueToRemove = new GenericDao<>(Venue.class).getById(1);
        testUser.removeVenueFollow(venueToRemove);
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int venueFollowsAfterRemove = userAfterUpdate.getVenueFollows().size();
        assertEquals(initialVenueFollowsSize - 1, venueFollowsAfterRemove);
    }
//
//    @Test
//    void addEventInterestSuccess() {
//        User testUser = dao.getById(1);
//        int initialEventInterestsSize = testUser.getEventInterests().size();
//
//        GenericDao<Event> eventDao = new GenericDao<>(Event.class);
//        Event eventToAdd = eventDao.getById(3);
//
//        testUser.addEventInterest(eventToAdd);
//        dao.update(testUser);
//
//        User userAfterUpdate = dao.getById(testUser.getId());
//        int afterUpdateEventInterestSize = userAfterUpdate.getEventInterests().size();
//
//        assertEquals(initialEventInterestsSize + 1, afterUpdateEventInterestSize);
//
//    }

    /**
     * remove interest in event 1 from user 1
     */
    @Test
    void removeEventInterestSuccess() {
        User testUser = dao.getById(1);
        int numInterestsBefore = testUser.getEventInterests().size();

        Event eventToRemove = new GenericDao<>(Event.class).getById(1);
        testUser.removeEventInterest(eventToRemove);
        dao.update(testUser);

        User userAfterUpdate = dao.getById(testUser.getId());
        int numInterestsAfter = userAfterUpdate.getEventInterests().size();
        assertEquals(numInterestsBefore - 1, numInterestsAfter);
    }
//    TODO ADDITIONAL TESTS
//    follow a venue
//    remove a performer follow
//    remove a venue follow
//    add event interest
//    remove event interest

}
