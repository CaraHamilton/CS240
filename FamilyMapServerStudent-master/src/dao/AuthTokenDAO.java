package dao;

import model.AuthToken;
import model.User;

import java.sql.*;

/**
 * Holds the methods to interact with the AuthToken Table
 */
public class AuthTokenDAO {
    private final Connection conn;

    public AuthTokenDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Inserts AuthToken into authToken table
     * @param authToken
     */
    public void add(AuthToken authToken) throws DataAccessException{
        String sql = "INSERT INTO authToken (authToken, personID) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthToken());
            stmt.setString(3, authToken.getUserID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds AuthToken in authToken table
     * @param authToken
     * @return AuthToken
     */
    public AuthToken find(String authToken) throws DataAccessException {
        AuthToken authtoken;
        ResultSet rs = null;
        String sql = "SELECT * FROM authToken WHERE authToken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtoken = new AuthToken(rs.getString("authToken"),
                        rs.getString("personID"));
                return authtoken;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding authToken");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Finds a user based on authToken and returns it
     * @param authToken
     * @return User
     */
    public User findUser(String authToken) {return null;}

    /**
     * Removes AuthToken from authToken table
     * @param authToken
     */
    public void remove(AuthToken authToken) {}

    /**
     * Empties authToken table
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM authToken;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new DataAccessException("Error encountered while clearing authToken table");
        }

    }

}
