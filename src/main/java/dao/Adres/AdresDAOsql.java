package dao.Adres;

import dao.Reiziger.ReizigerDAOsql;
import domein.Adres;
import domein.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOsql implements AdresDAO{
    private Connection connection;
    private ReizigerDAOsql reizigerDAOsql;

    public AdresDAOsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)");
            int adres_id = adres.getAdresId();
            String postcode = adres.getPostcode();
            String huisnummer = adres.getHuisnummer();
            String straat = adres.getStraat();
            String woonplaats = adres.getWoonplaats();
            int reiziger_id = adres.getReiziger().getReizigerId();
            statement.setInt(1, adres_id);
            statement.setString(2, postcode);
            statement.setString(3, huisnummer);
            statement.setString(4, straat);
            statement.setString(5, woonplaats);
            statement.setInt(6, reiziger_id);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String pss = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?,woonplaats = ? WHERE adres_id = ?";
            PreparedStatement statement = connection.prepareStatement(pss);

            statement.setString(1, adres.getPostcode());
            statement.setString(2, adres.getHuisnummer());
            statement.setString(3, adres.getStraat());
            statement.setString(4, adres.getWoonplaats());
            statement.setInt(5, adres.getAdresId());
            statement.execute();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM adres WHERE adres_id = ?");
            statement.setInt(1, adres.getAdresId());

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Adres findById(int adres_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from adres where adres_id = ?");
            statement.setInt(1, adres_id);
            ResultSet results = statement.executeQuery();

            results.next();
            String postcode = results.getString(2);
            String huisnummer = results.getString(3);
            String straat = results.getString(4);
            String woonplaats = results.getString(5);

            int reiziger_id = results.getInt(6);
            Reiziger reiziger = reizigerDAOsql.findById(reiziger_id);

            return new Adres(reiziger, adres_id, postcode, huisnummer, straat, woonplaats);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        int reiziger_id = reiziger.getReizigerId();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from adres where reiziger_id = ?");
            statement.setInt(1, reiziger_id);
            ResultSet results = statement.executeQuery();

            results.next();
            int adres_id = results.getInt(1);
            String postcode = results.getString(2);
            String huisnummer = results.getString(3);
            String straat = results.getString(4);
            String woonplaats = results.getString(5);


            return new Adres(reiziger, adres_id, postcode, huisnummer, straat, woonplaats);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from adres");
            ResultSet results = statement.executeQuery();

            List<Adres> adressen = new ArrayList<>();

            while(results.next()){
                int adres_id = results.getInt(1);
                String postcode = results.getString(2);
                String huisnummer = results.getString(3);
                String straat = results.getString(4);
                String woonplaats = results.getString(5);

                int reiziger_id = results.getInt(6);
                Reiziger reiziger = reizigerDAOsql.findById(reiziger_id);

                adressen.add(new Adres(reiziger, adres_id, postcode, huisnummer, straat, woonplaats));
            }
            return adressen;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void setReizigerDAOsql(ReizigerDAOsql reizigerDAOsql) {
        this.reizigerDAOsql = reizigerDAOsql;
    }
}
