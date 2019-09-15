package dao;

import java.util.ArrayList;

public class MainJDBC {
    public static void main(String[] args) {

        try {
            FacadeJDBC facadeJDBC = FacadeJDBC.getInstance();
            facadeJDBC.recreateAll();
            facadeJDBC.insertExamples(DbObjects.CLIENT, 14);
            facadeJDBC.insertExamples(DbObjects.COMPANY, 9);
            facadeJDBC.insertExamples(DbObjects.TICKET, 6);
            facadeJDBC.insertExamples(DbObjects.FLIGHT, 5);
            facadeJDBC.showDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
