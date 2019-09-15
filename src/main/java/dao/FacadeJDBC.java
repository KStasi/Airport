package dao;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class FacadeJDBC {
    protected ClientDao clientDao = null;
    protected FlightDao flightDao = null;
    protected TicketDao ticketDao = null;
    protected CompanyDao companyDao = null;
    protected List<String> namesSamples = null;
    protected List<String> surnamesSamples = null;
    protected List<String> citiesSamples = null;

    public static FacadeJDBC getInstance() {
        return FacadeJDBCSingleton.INSTANCE;
    }
    protected DaoFactory df = null;

    public void recreateAll() {
        this.clientDao.deleteTable();
        this.flightDao.deleteTable();
        this.ticketDao.deleteTable();
        this.companyDao.deleteTable();

        this.clientDao.createTable();
        this.flightDao.createTable();
        this.ticketDao.createTable();
        this.companyDao.createTable();
    }

    public void insertExamples(DbObjects o, Integer counter) {

    }

    public void modifyByIndex(DbObjects o, Integer index) {

    }

    public void listAll(DbObjects o) {

    }

    public void deleteAll() {
        this.clientDao.deleteTable();
        this.flightDao.deleteTable();
        this.ticketDao.deleteTable();
        this.companyDao.deleteTable();
    }

    private static class FacadeJDBCSingleton {

        public static final FacadeJDBC INSTANCE;
        static
        {
            FacadeJDBC facadeJDBC;
            try {
                facadeJDBC = new FacadeJDBC();
                facadeJDBC.df = DaoFactory.getInstance();
                facadeJDBC.clientDao = (ClientDao) facadeJDBC.df.getDAO(DbObjects.CLIENT);
                facadeJDBC.flightDao = (FlightDao) facadeJDBC.df.getDAO(DbObjects.FLIGHT);
                facadeJDBC.ticketDao = (TicketDao) facadeJDBC.df.getDAO(DbObjects.TICKET);
                facadeJDBC.companyDao = (CompanyDao) facadeJDBC.df.getDAO(DbObjects.COMPANY);

                JSONParser parser = new JSONParser();
                try
                {
                    Object namesObject = parser
                            .parse(new FileReader(facadeJDBC.getClass().getClassLoader().getResource("name.json").getFile()));
                    Object surnamesObject = parser
                            .parse(new FileReader(facadeJDBC.getClass().getClassLoader().getResource("surname.json").getFile()));
                    Object citiesObject = parser
                            .parse(new FileReader(facadeJDBC.getClass().getClassLoader().getResource("city.json").getFile()));

                    JSONArray names = (JSONArray)namesObject;
                    JSONArray surnames = (JSONArray)surnamesObject;
                    JSONArray cities = (JSONArray)citiesObject;
                    facadeJDBC.namesSamples = names;
                    facadeJDBC.surnamesSamples = surnames;
                    facadeJDBC.citiesSamples = cities;

                    for(Object country : facadeJDBC.namesSamples)
                    {
                        System.out.println("\t"+country.toString());
                    }
                }
                catch(FileNotFoundException fe)
                {
                    fe.printStackTrace();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            }
            catch(Exception e){
                facadeJDBC = null;
            }
            INSTANCE = facadeJDBC;
        }

    }
}
