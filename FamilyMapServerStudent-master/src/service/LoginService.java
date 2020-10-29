package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import result.LoginResult;

import java.util.UUID;

/*
Usually has one method
create a database
open a connection
use DAO class to do what is needed
close connection
create and return result object
 */

public class LoginService {

    public LoginResult login(LoginRequest r) throws DataAccessException{
        Database db = new Database();

        try {
            db.openConnection();
            System.out.println("in login server");
            UserDAO userdao = new UserDAO(db.getConnection());
            AuthTokenDAO authTokendao = new AuthTokenDAO(db.getConnection());

            String username = userdao.find(r.getUserName()).getUsername();
            String personID = userdao.find(r.getUserName()).getPersonID();

            System.out.println(username + " " + personID);

            //generate new authToken
            String authString = UUID.randomUUID().toString().replace("-", "");
            AuthToken authToken = new AuthToken(authString, personID);

            //actual database operations
            authTokendao.add(authToken);

            db.closeConnection(true);

            LoginResult rslt = new LoginResult(authString, username, personID, true);
            return rslt;
        }
        catch (DataAccessException e) {
            db.closeConnection(false);
            LoginResult rslt = new LoginResult("Server error", false);
            return rslt;

        }
    }
}
