package dao;

import com.mongodb.DB;
import com.mongodb.DBObject;

import java.sql.*;

public class FlightDaoNoSql extends DaoNoSqlGeneric<Flight> {
    public Flight newInstance(DBObject resultSet) {
        Integer id = (Integer) resultSet.get("id");
        Integer boardId = (Integer) resultSet.get("boardId");
        Integer companyId = (Integer) resultSet.get("companyId");
        Integer placesCounter = (Integer) resultSet.get("placesCounter");
        String cityFrom  = (String) resultSet.get("cityFrom");
        String cityTo = (String) resultSet.get("cityTo");
        Date departure = (Date) resultSet.get("departure");
        Date arrival = (Date) resultSet.get("arrival");
        return new Flight(id, companyId, boardId, placesCounter, cityFrom, cityTo, departure, arrival);
    }


    public DBObject toDBObject(Flight t) {
        return t.toDBObject();
    }

    public FlightDaoNoSql(DB con) {
        super("flight");
        this.db = con;
    }
}
