package dao;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private Database db;
    private User bestUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new user with random data
        bestUser = new User("lil_sebastian", "lil_seb", "sebastian@123.com",
                "lil", "sebastian", "m", "12345");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        db.clearTables();
        //Then we pass that connection to the UserDAO so it can access the database
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void registerPass() throws DataAccessException{
        uDao.register(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void registerFail() throws DataAccessException {
        uDao.register(bestUser);
        assertThrows(DataAccessException.class, ()->uDao.register(bestUser));
    }

    @Test
    public void findPass() throws DataAccessException{
        uDao.register(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);

    }

    @Test
    public void findFail() throws DataAccessException{
        //uDao.register(bestUser);
        User failTest = uDao.find(bestUser.getUsername());
        assertNull(failTest);
        //assertThrows(DataAccessException.class, ()->uDao.find(bestUser.getUsername()));
    }

    @Test
    void clear() throws DataAccessException {
        uDao.register(bestUser);
        User findTest = uDao.find(bestUser.getUsername());
        uDao.clear();
        User failTest = uDao.find(bestUser.getUsername());
        assertNotNull(findTest);
        assertNull(failTest);
    }
}