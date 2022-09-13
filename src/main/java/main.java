import dao.Adres.AdresDAOsql;
import dao.Reiziger.ReizigerDAOsql;
import domein.Adres;
import domein.Reiziger;

import java.sql.*;
import java.util.List;

public class main {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ovchip";
        String username = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            ReizigerDAOsql reizigerDAOsql = new ReizigerDAOsql(connection);
            AdresDAOsql adresDAOsql = new AdresDAOsql(connection);

            reizigerDAOsql.setAdresDAOsql(adresDAOsql);
            adresDAOsql.setReizigerDAOsql(reizigerDAOsql);


            // save test

            String gbdatum = "1981-03-14";
            Reiziger sietske = new Reiziger(78, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
            System.out.println(reizigerDAOsql.save(sietske));

            // update test

            Reiziger updatedSietske = new Reiziger(78, "S", "van", "Specht", java.sql.Date.valueOf(gbdatum));
            System.out.println((reizigerDAOsql.update(updatedSietske)));

            // delete test

            System.out.println(reizigerDAOsql.delete(updatedSietske));

            // find by id test
            System.out.println(reizigerDAOsql.findById(1).toString());

            // find by datum test

            List<Reiziger> reizigers = reizigerDAOsql.findByGbdatum("2002-12-03");
            System.out.println("\ndatum test\n");
            for (Reiziger perReiziger : reizigers) {
                System.out.println(perReiziger.toString());
                System.out.println("\n");
            }

            // findall test

            List<Reiziger> reizigers2 = reizigerDAOsql.findAll();
            System.out.println("\nfindall test\n");
            for (Reiziger perReiziger : reizigers2) {
                System.out.println(perReiziger.toString());
                System.out.println("\n");
            }

            // adres tests

            // reiziger aanmaken om mee te testen en linken

            Reiziger reiziger = new Reiziger(10, "A", "van", "Zwan", Date.valueOf("2003-07-30"));
            reizigerDAOsql.save(reiziger);

            //adres save test

            Adres adres = new Adres(reizigerDAOsql.findById(10), 10, "2420DH", "20", "Winde", "Gouda");
            System.out.println("adres save test, TRUE al gaat het goed");
            System.out.println(adresDAOsql.save(adres));

            //adres update test

            Adres nieuwAdres = new Adres(reizigerDAOsql.findById(10), 10, "2420DH", "30", "Winde", "Rotterdam");
            System.out.println("\nadres update test, TRUE al gaat het goed");
            System.out.println(adresDAOsql.update(nieuwAdres));

            // adres update test

            System.out.println("\nadres delete test, TRUE al gaat het goed");
            System.out.println(adresDAOsql.delete(nieuwAdres));

            //adres find by id test

            adresDAOsql.save(adres);
            System.out.println("\nadres find by id test, adres.toString al gaat het goed");
            System.out.println(adresDAOsql.findById(10));

            // adres find by reiziger test

            System.out.println("\nadres find by reiziger test, adres.toString al gaat het goed");
            System.out.println(adresDAOsql.findByReiziger(reiziger));

            //adres findall

            System.out.println("\n adres find all, meerdere adres.toString al gaat het goed");
            for (Adres perAdres : adresDAOsql.findAll()) {
                System.out.println(perAdres);
            }




            connection.close();
        } catch (Exception e) {
            System.out.println("er is iets fout gegaan:");
            System.out.println(e);
        }

    }
}
