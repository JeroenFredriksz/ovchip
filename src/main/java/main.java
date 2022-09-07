import dao.Reiziger.ReizigerDAOsql;
import domein.Reiziger;

import java.sql.*;
import java.util.List;

public class main {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        String username = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            ReizigerDAOsql dao = new ReizigerDAOsql(connection);


            // save test

            String gbdatum = "1981-03-14";
            Reiziger sietske = new Reiziger(78, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
            System.out.println(dao.save(sietske));

            // update test

            Reiziger updatedSietske = new Reiziger(78, "S", "van", "Specht", java.sql.Date.valueOf(gbdatum));
            System.out.println((dao.update(updatedSietske)));

            // delete test

            System.out.println(dao.delete(updatedSietske));

            // find by id test
            System.out.println(dao.findById(1).toString());

            // find by datum test

            List<Reiziger> reizigers = dao.findByGbdatum("2002-12-03");
            System.out.println("\ndatum test\n");
            for (Reiziger perReiziger : reizigers) {
                System.out.println(perReiziger.toString());
                System.out.println("\n");
            }

            // findall test

            List<Reiziger> reizigers2 = dao.findAll();
            System.out.println("\nfindall test\n");
            for (Reiziger perReiziger : reizigers2) {
                System.out.println(perReiziger.toString());
                System.out.println("\n");
            }



            connection.close();
        } catch (Exception e) {
            System.out.println("er is iets fout gegaan:");
            System.out.println(e);
        }

    }
}
