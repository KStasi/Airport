package dao;

import java.sql.*;

public class FlightDao extends DaoGeneric<Flight> {
    public Flight newInstance(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer boardId = resultSet.getInt("boardId");
        Integer companyId = resultSet.getInt("companyId");
        Integer placesCounter = resultSet.getInt("placesCounter");
        String cityFrom  = resultSet.getString("cityFrom");
        String cityTo = resultSet.getString("cityTo");
        Date departure = resultSet.getDate("departure");
        Date arrival = resultSet.getDate("arrival");
        return new Flight(id, companyId, boardId, placesCounter, cityFrom, cityTo, departure, arrival);
    }

    public PreparedStatement prepareStatement(PreparedStatement preparedStatement, Flight t) throws SQLException{
        preparedStatement.setInt(1, t.boardId);
        preparedStatement.setInt(2, t.companyId);
        preparedStatement.setInt(3, t.placesCounter);
        preparedStatement.setString(4, t.cityFrom);
        preparedStatement.setString(5, t.cityTo);
        preparedStatement.setDate(6, t.departure);
        preparedStatement.setDate(7, t.arrival);
        return preparedStatement;
    }
    public FlightDao(Connection con) {
        super("flight", "boardId, companyId, placesCounter, cityFrom, cityTo, departure, arrival", "CREATE TABLE flight (\n" +
                "    id serial PRIMARY KEY,\n" +
                "    boardId integer NOT NULL,\n" +
                "    companyId integer NOT NULL,\n" +
                "    placesCounter integer NOT NULL,\n" +
                "    cityFrom char (50) NOT NULL,\n" +
                "    cityTo char (50) NOT NULL,\n" +
                "    departure date NOT NULL,\n" +
                "    arrival date NOT NULL\n" +
                ");");
        this.con = con;
    }
}
