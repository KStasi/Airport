package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDaoSql extends DaoSqlGeneric<Ticket> {

    public Ticket newInstance(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer flightId = resultSet.getInt("flightId");
        Integer clientId = resultSet.getInt("clientId");
        Long price = resultSet.getLong("price");
        Integer place = resultSet.getInt("place");
        String type = resultSet.getString("type");
        return new Ticket(id, flightId, clientId, price, place, type);
    }

    public PreparedStatement prepareStatement(PreparedStatement preparedStatement, Ticket t) throws SQLException{
        preparedStatement.setInt(1, t.flightId);
        preparedStatement.setInt(2, t.clientId);
        preparedStatement.setLong(3, t.price);
        preparedStatement.setInt(4, t.place);
        preparedStatement.setString(5, t.type);
        return preparedStatement;
    }

    public TicketDaoSql(Connection con) {
        super("ticket", "flightId, clientId, price, place, type", "?, ?, ?, ?, ?", "CREATE TABLE ticket (\n" +
                "    id serial PRIMARY KEY,\n" +
                "    flightId integer NOT NULL,\n" +
                "    clientId integer NOT NULL,\n" +
                "    price bigint NOT NULL,\n" +
                "    place integer NOT NULL,\n" +
                "    type char (1) NOT NULL\n" +
                ");");
        this.con = con;
    }
}
