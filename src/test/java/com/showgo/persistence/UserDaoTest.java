package com.showgo.persistence;

import com.showgo.entity.User;
import com.showgo.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserDaoTest {
    UserDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        logger.debug("setup runs in UserDaoTest");
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userDao = new UserDao();
    }

    @Test
    void getById() {
        logger.debug("getById runs in UserDaoTest");
        User retrievedUser = userDao.getById(1);
        logger.debug(retrievedUser);
        assertEquals("user1", retrievedUser.getUsername());
    }
}
