package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao extends DaoGeneric<Company> {
    public Company newInstance(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String directorName = resultSet.getString("directorName");
        String directorSurname = resultSet.getString("directorSurname");
        String phone = resultSet.getString("phone");
        return new Company(id, name, directorName, directorSurname, phone);
    }

    public PreparedStatement prepareStatement(PreparedStatement preparedStatement, Company t) throws SQLException{
        preparedStatement.setString(1, t.name);
        preparedStatement.setString(2, t.directorName);
        preparedStatement.setString(3, t.directorSurname);
        preparedStatement.setString(4, t.phone);
        return preparedStatement;
    }

    public CompanyDao(Connection con) {
        super("company", "name, directorName, directorSurname, phone", "?, ?, ?, ?", "CREATE TABLE company (\n" +
                "    id serial PRIMARY KEY,\n" +
                "    name varchar (50) NOT NULL,\n" +
                "    directorName varchar (50) NOT NULL,\n" +
                "    directorSurname varchar (50) NOT NULL,\n" +
                "    phone varchar (11) NOT NULL\n" +
                ");");
        this.con = con;
    }
}
