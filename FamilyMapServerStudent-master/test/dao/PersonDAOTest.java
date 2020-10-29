package dao;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException{
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new user with random data
        bestPerson = new Person("12345", "lil_sebastian", "lil",
                "sebastian", "m", "13579", "2468", "56789");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        db.clearTables();
        //Then we pass that connection to the UserDAO so it can access the database
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, () -> pDao.insert(bestPerson));
    }

    @Test
    public void findPass() throws DataAccessException{
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException{
        Person failTest = pDao.find(bestPerson.getPersonID());
        assertNull(failTest);
    }

    @Test
    void clear() throws DataAccessException {
        pDao.insert(bestPerson);
        Person findTest = pDao.find(bestPerson.getPersonID());
        pDao.clear();
        Person clearTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(findTest);
        assertNull(clearTest);
    }
}