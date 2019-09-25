package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DaoSqlGeneric<T> implements CRUDInterface<T>{
    protected Connection con;
    private static String SQL_SELECT = "SELECT * FROM %s WHERE id=?;";
    private static String SQL_DELETE = "DELETE FROM %s WHERE id=?;";
    private static String SQL_DELETE_ALL = "DROP TABLE %s;";
    private static String SQL_INSERT = "INSERT INTO %s (%s) VALUES (%s);";
    private static String SQL_UPDATE = "UPDATE %s SET %s=? WHERE id=?;";
    private static String SQL_SELECT_ALL = "SELECT * FROM %s;";
    private String tableName;
    private String tableParans;
    private String tableValues;
    private String createAllStatement;

    public void createTable() {
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(createAllStatement);

            int result = preparedStatement.executeUpdate();
            System.out.print("Table created\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        try {
            String deleteStatement = String.format(SQL_DELETE_ALL, this.tableName);
            PreparedStatement preparedStatement = this.con.prepareStatement(deleteStatement);

            int result = preparedStatement.executeUpdate();
            System.out.print("Table deleted\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public Optional<T> read(Integer id) {
        try {
            String readStatement = String.format(SQL_SELECT, this.tableName);
            PreparedStatement preparedStatement = this.con.prepareStatement(readStatement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = newInstance(resultSet);
                return Optional.of(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    };

    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try {
            String getStatement = String.format(SQL_SELECT_ALL, this.tableName);
            PreparedStatement preparedStatement = this.con.prepareStatement(getStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = newInstance(resultSet);
                result.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    };

    public void create(T t) {
        try {
            String createStatement = String.format(SQL_INSERT, this.tableName, this.tableParans, this.tableValues);
            PreparedStatement preparedStatement = this.con.prepareStatement(createStatement);
            preparedStatement = prepareStatement(preparedStatement, t);

            int result = preparedStatement.executeUpdate();
            System.out.printf("%d rows added\n", result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public abstract PreparedStatement prepareStatement(PreparedStatement preparedStatement, T t) throws SQLException;
    public abstract T newInstance(ResultSet resultSet) throws SQLException;

    public void delete(Integer id) {
        try {
            String deleteStatement = String.format(SQL_DELETE, this.tableName);
            PreparedStatement preparedStatement = this.con.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
            System.out.printf("%d rows deleted\n", result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };


    public void update(T t, String atribute, Map<String, Object> params) {
        try {
            String updateStatement = String.format(SQL_UPDATE, this.tableName, atribute);
            PreparedStatement preparedStatement = this.con.prepareStatement(updateStatement);
            Object param = params.get(atribute);
            if (param instanceof String) {
                preparedStatement.setString(1, (String) param);
            }
            if (param instanceof Integer) {
                preparedStatement.setInt(1, (Integer) param);
            }
            if (param instanceof Date) {
                preparedStatement.setDate(1, (Date) param);
            }
            if (param instanceof Long) {
                preparedStatement.setLong(1, (Long) param);
            }
            int result = preparedStatement.executeUpdate();
            System.out.printf("%d rows updated\n", result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public DaoSqlGeneric(String tableName, String params, String values, String createAllStatement) {
        this.tableName = tableName;
        this.tableParans = params;
        this.tableValues = values;
        this.createAllStatement = createAllStatement;
    }
}
