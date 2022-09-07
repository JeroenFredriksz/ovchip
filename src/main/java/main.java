import java.sql.*;

public class main {
    public static void main(String[] args){
        String url = "jdbc:postgresql:ovchip";
        String username = "postgres";
        String password = "postgres";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement statement = db.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM reiziger");
            System.out.println("alle reizigers: ");
            while (results.next())
            {
                String voorletter = results.getString(2);
                String tussenvoegsel = " " + results.getString(3);
                String achternaam = results.getString(4);

                if (tussenvoegsel.equals(" null")) {
                    tussenvoegsel = "";
                }
                String datum = results.getString(5);
                System.out.println(String.format("%s.%s %s (%s)", voorletter, tussenvoegsel, achternaam, datum));
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("er is iets fout gegaan:");
            System.out.println(e);
        }

    }
}
