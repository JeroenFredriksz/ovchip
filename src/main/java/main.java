import dao.Reiziger.ReizigerDAOsql;
import domein.Reiziger;

import java.sql.*;

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



            connection.close();
        } catch (Exception e) {
            System.out.println("er is iets fout gegaan:");
            System.out.println(e);
        }

    }
}
