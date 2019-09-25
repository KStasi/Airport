package dao;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoNoSql extends DaoNoSqlGeneric<Client> {

    public Client newInstance(DBObject resultSet) {
        Integer id = (Integer) resultSet.get("id");
        String name = (String) resultSet.get("name");
        String surname = ( String) resultSet.get("surname");
        Long pasportId = (Long)resultSet.get("pasportId");
        Integer discount = (Integer) resultSet.get("discount");
        String phone = (String) resultSet.get("phone");
        return new Client(id, name, surname, pasportId, discount, phone);
    }

    public DBObject toDBObject(Client t) {
        return t.toDBObject();
    }

    public ClientDaoNoSql(DB db) {
        super("client");
        this.db = db;
    }
}
