package dao.Reiziger;

import dao.Adres.AdresDAOsql;
import dao.Ov_chipkaart.Ov_chipkaartDAOsql;
import domein.Adres;
import domein.Ov_chipkaart;
import domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql implements ReizigerDAO {
    private Connection connection;
    private AdresDAOsql adresDAOsql;
    private Ov_chipkaartDAOsql ov_chipkaartDAOsql;

    public ReizigerDAOsql (Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean save(Reiziger reiziger) {
        try {
            String prepareStatementString = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(prepareStatementString);
            statement.setInt(1, reiziger.getReizigerId());
            statement.setString(2, reiziger.getVoorletters());
            statement.setString(3, reiziger.getTussenvoegsel());
            statement.setString(4, reiziger.getAchternaam());
            statement.setDate(5, reiziger.getGeboortedatum());

            statement.execute();
            statement.close();

            if (reiziger.getAdres() != null) {
                adresDAOsql.save(reiziger.getAdres());
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String pss = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
            PreparedStatement statement = connection.prepareStatement(pss);
            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsel());
            statement.setString(3, reiziger.getAchternaam());
            statement.setDate(4, reiziger.getGeboortedatum());
            statement.setInt(5, reiziger.getReizigerId());
            statement.execute();
            statement.close();

            if (reiziger.getAdres() != null) {
                adresDAOsql.update(reiziger.getAdres());
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            if (reiziger.getAdres() != null) {
                adresDAOsql.delete(reiziger.getAdres());
            }
            if (reiziger.getChipkaarten().size() > 0) {
                for (Ov_chipkaart perChipkaart : reiziger.getChipkaarten()) {
                    ov_chipkaartDAOsql.delete(perChipkaart);
                }
            }

            PreparedStatement statement = connection.prepareStatement("DELETE From reiziger WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getReizigerId());

            statement.execute();
            statement.close();


            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger where reiziger_id = ?");
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();

            // maak van de resultset een reiziger en geef deze terug
            results.next();
            Reiziger reiziger = krijgReizigerResultset(results);

            Adres adres = adresDAOsql.findByReiziger(reiziger);
            if (adres != null) {
                reiziger.setAdres(adres);
            }
            statement.close();
            return reiziger;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum = ?");
            Date date = Date.valueOf(datum);
            statement.setDate(1, date);

            ResultSet results = statement.executeQuery();
            List<Reiziger> reizigers = new ArrayList<>();
            while (results.next()) {
                Reiziger reiziger = krijgReizigerResultset(results);
                Adres adres = adresDAOsql.findByReiziger(reiziger);
                if (adres != null) {
                    reiziger.setAdres(adres);
                }
                reizigers.add(reiziger);

            }
            statement.close();
            return reizigers;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM reiziger;");
            List<Reiziger> reizigers = new ArrayList<>();

            while(results.next()) {
                Reiziger reiziger = krijgReizigerResultset(results);
                Adres adres = adresDAOsql.findByReiziger(reiziger);
                if (adres != null) {
                    reiziger.setAdres(adres);
                }
                 reizigers.add(reiziger);
            }
            statement.close();
            return reizigers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Reiziger krijgReizigerResultset(ResultSet results) throws SQLException {
        int id = results.getInt(1);
        String voorletters = results.getString(2);
        String tussenvoegsel = results.getString(3);
        String achternaam = results.getString(4);
        Date geboortedatum = results.getDate(5);
        return new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);
    }

    public void setAdresDAOsql(AdresDAOsql adresDAOsql) {
        this.adresDAOsql = adresDAOsql;
    }

    public void setOv_chipkaartDAOsql(Ov_chipkaartDAOsql ov_chipkaartDAOsql) {
        this.ov_chipkaartDAOsql = ov_chipkaartDAOsql;
    }
}
