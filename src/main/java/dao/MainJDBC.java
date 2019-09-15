package dao;

import java.util.ArrayList;

public class MainJDBC {
    public static void main(String[] args) {

        try {
//            DaoFactory daoFactory = DaoFactory.getInstance();
//            ClientDao clientDao = (ClientDao) daoFactory.getDAO(DbObjects.CLIENT);
//            clientDao.deleteTable();
//            clientDao.createTable();
//            Client client1 = new Client(0,"Kate", "Rigo", 54333453L, 0, "80972592169");
//            clientDao.create(client1);
//            ArrayList clients = (ArrayList) clientDao.getAll();

//            for (Object client : clients) {
//                System.out.print(((Client)client).name);
//            }

            FacadeJDBC facadeJDBC = FacadeJDBC.getInstance();
            facadeJDBC.recreateAll();
            facadeJDBC.insertExamples(DbObjects.COMPANY, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
