package dao;

import com.mongodb.DB;
import com.mongodb.DBObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDaoNoSql extends DaoNoSqlGeneric<Ticket> {

    public Ticket newInstance(DBObject resultSet) {
        Integer id = (Integer) resultSet.get("id");
        Integer flightId = (Integer)resultSet.get("flightId");
        Integer clientId = (Integer)resultSet.get("clientId");
        Long price = (Long) resultSet.get("price");
        Integer place = (Integer) resultSet.get("place");
        String type = (String) resultSet.get("type");
        return new Ticket(id, flightId, clientId, price, place, type);
    }

    public DBObject toDBObject(Ticket t) {
        return t.toDBObject();
    }

    public TicketDaoNoSql(DB con) {
        super("ticket");
        this.db = con;
    }
}
