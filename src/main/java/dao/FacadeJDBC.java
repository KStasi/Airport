package dao;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
        IntStream.range(0, counter).forEachOrdered(n -> {
            switch (o){
                case CLIENT: {
                    this.clientDao.create(new Client(0,
                            this.namesSamples.get(new Random().nextInt(this.namesSamples.size())),
                            this.surnamesSamples.get(new Random().nextInt(this.surnamesSamples.size())),
                            new Random().nextLong(),
                            new Random().nextInt(),
                            this.namesSamples.get(new Random().nextInt(this.namesSamples.size()))
                            ));
                    break;
                }
                case FLIGHT: {
                    this.flightDao.create(new Flight(0,
                            new Random().nextInt(),
                            new Random().nextInt(),
                            new Random().nextInt(),
                            this.citiesSamples.get(new Random().nextInt(this.citiesSamples.size())),
                            this.citiesSamples.get(new Random().nextInt(this.citiesSamples.size())),
                            RandomDates.createRandomDate(2019, 1020),
                            RandomDates.createRandomDate(2019, 1020)
                            ));
                    break;
                }
                case TICKET: {
                    this.ticketDao.create(new Ticket(0,
                            new Random().nextInt(),
                            new Random().nextInt(),
                            new Random().nextLong(),
                            new Random().nextInt(),
                            this.citiesSamples.get(new Random().nextInt(this.citiesSamples.size())).substring(0, 1)
                            ));
                    break;
                }
                case COMPANY: {
                    this.companyDao.create(new Company(0,
                            this.namesSamples.get(new Random().nextInt(this.namesSamples.size())),
                            this.namesSamples.get(new Random().nextInt(this.namesSamples.size())),
                            this.surnamesSamples.get(new Random().nextInt(this.surnamesSamples.size())),
                            this.surnamesSamples.get(new Random().nextInt(this.surnamesSamples.size()))
                            ));
                    break;
                }
            }
        });
    }

    public void modifyByIndex(DbObjects o, Integer index) {

    }

    public void showDb() {
        ArrayList clients = (ArrayList) this.clientDao.getAll();
        ArrayList flights = (ArrayList) this.flightDao.getAll();
        ArrayList companies = (ArrayList) this.companyDao.getAll();
        ArrayList tickets = (ArrayList) this.ticketDao.getAll();

        for (Object client : clients) {
            System.out.print((Client)client);
        }

        for (Object flight : flights) {
            System.out.print((Flight)flight);
        }

        for (Object company : companies) {
            System.out.print((Company)company);
        }

        for (Object ticket : tickets) {
            System.out.print((Ticket)ticket);
        }
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
