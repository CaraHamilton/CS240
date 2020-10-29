package service;

import dao.DataAccessException;
import dao.Database;
import result.ClearResult;


public class ClearService {

    public ClearResult clear() throws DataAccessException{
        Database db = new Database();
        try {
            db.openConnection();
            db.clearTables();
            db.closeConnection(true);
            ClearResult rslt = new ClearResult(true, "Clear succeeded");
            return rslt;

        } catch (DataAccessException e) {
            db.closeConnection(false);
            ClearResult rslt = new ClearResult(false, "Server error");
            return rslt;
        }
    }
}
