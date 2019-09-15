package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao extends DaoGeneric<Client> {

    public Client newInstance(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Long pasportId = resultSet.getLong("pasportId");
        Integer discount = resultSet.getInt("discount");
        String phone = resultSet.getString("phone");
        return new Client(id, name, surname, pasportId, discount, phone);
    }

    public PreparedStatement prepareStatement(PreparedStatement preparedStatement, Client t) throws SQLException{
        preparedStatement.setString(1, t.name);
        preparedStatement.setString(2, t.surname);
        preparedStatement.setLong(3, t.pasportId);
        preparedStatement.setInt(4, t.discount);
        preparedStatement.setString(5, t.phone);
        return preparedStatement;
    }

    public ClientDao(Connection con) {
        super("client", "name, surname, pasportId, discount, phone", "?, ?, ?, ?, ?",
                "CREATE TABLE client (\n" +
                "    id serial PRIMARY KEY,\n" +
                "    name varchar (50) NOT NULL,\n" +
                "    surname varchar (50) NOT NULL,\n" +
                "    pasportId bigint NOT NULL,\n" +
                "    discount integer NOT NULL,\n" +
                "    phone varchar (25) NOT NULL\n" +
                ");");
        this.con = con;
    }
}
