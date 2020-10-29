package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

import java.util.UUID;

/**
 * Creates a new user account, generates 4 generations of ancestor data for the new user, logs the user in, and
 * returns an auth token.
 */
public class RegisterService {

    /**
     * Passes in a register request from the user and returns a result
     * @param r
     * @return RegisterResult
     */
    public RegisterResult register(RegisterRequest r) throws DataAccessException {
        Database db = new Database();

        try {
            db.openConnection();
            UserDAO userdao = new UserDAO(db.getConnection());

            String username = r.getUsername();
            String password = r.getPassword();
            String email = r.getEmail();
            String firstName = r.getFirstName();
            String lastname = r.getLastName();
            String gender = r.getGender();
            String personID = UUID.randomUUID().toString().replace("-", "");

            User newUser = new User(username, password, email, firstName, lastname, gender, personID);

            userdao.register(newUser);

            //log user in
            LoginRequest loginRequest = new LoginRequest(username, password);
            LoginService loginService = new LoginService();
            LoginResult loginResult = loginService.login(loginRequest);
            String authToken = loginResult.getAuthToken();


            db.closeConnection(true);

            RegisterResult rslt = new RegisterResult(authToken, username, password, "success", true);
            return rslt;

        } catch (DataAccessException e) {
            db.closeConnection(false);
            RegisterResult rslt = new RegisterResult("Server error", false);
            return rslt;
        }
    }
}
