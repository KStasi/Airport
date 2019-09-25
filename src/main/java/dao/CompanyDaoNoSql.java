package dao;

import com.mongodb.DB;
import com.mongodb.DBObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDaoNoSql extends DaoNoSqlGeneric<Company> {
    public Company newInstance(DBObject resultSet) {
        Integer id = (Integer) resultSet.get("id");
        String name = (String)resultSet.get("name");
        String directorName = (String)resultSet.get("directorName");
        String directorSurname = (String)resultSet.get("directorSurname");
        String phone = (String)resultSet.get("phone");
        return new Company(id, name, directorName, directorSurname, phone);
    }

    public DBObject toDBObject(Company t) {
        return t.toDBObject();
    }


    public CompanyDaoNoSql(DB con) {
        super("company");
        this.db = con;
    }
}
