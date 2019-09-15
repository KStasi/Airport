package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    static private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/fly";
    static private final String USER = "fly";
    static private final String PASS = "pie";

    public static DaoFactory getInstance() {
        return DaoFactorySingleton.INSTANCE;
    }

    public DaoGeneric getDAO(DbObjects db) {

        try
        {
            if(this.con == null || this.con.isClosed()) //Let's ensure our connection is open
                this.open();
        }
        catch(SQLException e){ e.printStackTrace(); }

        switch(db)
        {
            case CLIENT:
                return new ClientDao(this.con);
            case COMPANY:
                return new CompanyDao(this.con);
            case TICKET:
                return new TicketDao(this.con);
            case FLIGHT:
                return new FlightDao(this.con);
            default:
                System.out.println("Trying to link to an non-existed table.");
        }
        return null;
    };

    public void open() throws SQLException {
        try
        {
            if(this.con==null || this.con.isClosed())
                this.con = DriverManager.getConnection(
                        DB_URL, USER, PASS);
        }
        catch(SQLException e) {
            throw e;
        }
    }

    public void close() throws SQLException {
        try
        {
            if(this.con!=null && this.con.isClosed())
                this.con.close();
        }
        catch(SQLException e) { throw e; }
    }

    private Connection con;

    private DaoFactory() throws Exception {
        try {
            Connection con = DriverManager.getConnection(
                    DB_URL, USER, PASS);

            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            this.con = con;
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
        finally{  }

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
