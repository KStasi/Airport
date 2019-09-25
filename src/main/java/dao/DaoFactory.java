package dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    static private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/fly";
    static private final String USER = "fly";
    static private final String PASS = "pie";

    public static DaoFactory getInstance() {
        return DaoFactorySingleton.INSTANCE;
    }

    public  CRUDInterface<? extends Object> getDAO(DbObjects dbo, DbTypes t) {

        try
        {
            if(this.sqlConnection == null || this.sqlConnection.isClosed() || this.mongoClient == null) //Let's ensure our connection is open
                this.open();
        }
        catch(SQLException e){ e.printStackTrace(); }

        if (t == DbTypes.POSTGRES) {
            switch(dbo)
            {
                case CLIENT:
                    return new ClientDaoSql(this.sqlConnection);
                case COMPANY:
                    return new CompanyDaoSql(this.sqlConnection);
                case TICKET:
                    return new TicketDaoSql(this.sqlConnection);
                case FLIGHT:
                    return new FlightDaoSql(this.sqlConnection);
                default:
                    System.out.println("Trying to link to an non-existed table.");
            }
        } else {
            switch(dbo)
            {
                case CLIENT:
                    return new ClientDaoNoSql(this.mongoDb);
                case COMPANY:
                    return new CompanyDaoNoSql(this.mongoDb);
                case TICKET:
                    return new TicketDaoNoSql(this.mongoDb);
                case FLIGHT:
                    return new FlightDaoNoSql(this.mongoDb);
                default:
                    System.out.println("Trying to link to an non-existed table.");
            }
        }
        return null;
    };

    public void open() {
        try
        {
            if(this.sqlConnection ==null || this.sqlConnection.isClosed())
                this.sqlConnection = DriverManager.getConnection(
                        DB_URL, USER, PASS);
            if (this.mongoClient == null)
                this.mongoClient = new MongoClient();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try
        {
            if(this.sqlConnection !=null && this.sqlConnection.isClosed())
                this.sqlConnection.close();
            if (this.mongoClient != null)
                this.mongoClient.close();
        }
        catch(SQLException e) { e.printStackTrace(); }
    }

    private Connection sqlConnection;
    private Mongo mongoClient;
    private DB mongoDb;

    private DaoFactory() {
        try {
            Connection con = DriverManager.getConnection(
                    DB_URL, USER, PASS);
            Mongo mongoClient = new Mongo();
            DB mongoDb = new DB(mongoClient, "fly");

            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            this.sqlConnection = con;
            this.mongoClient = mongoClient;
            this.mongoDb = mongoDb;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void finalize() throws SQLException
    {

        try{ this.close(); }
        finally{
        }
        System.out.println("Disconnected to the database!");

    }

    private static class DaoFactorySingleton {

        public static final DaoFactory INSTANCE;
        static
        {
            DaoFactory dm;
            try {
                dm = new DaoFactory();
            }
            catch(Exception e){
                dm = null;
            }
            INSTANCE = dm;
        }

    }
}
