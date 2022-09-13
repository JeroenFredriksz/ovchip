package dao.Ov_chipkaart;

import dao.Adres.AdresDAOsql;
import dao.Reiziger.ReizigerDAOsql;
import domein.Ov_chipkaart;
import domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ov_chipkaartDAOsql implements Ov_chipkaartDAO{
    private Connection connection;
    private ReizigerDAOsql reizigerDAOsql;

    public Ov_chipkaartDAOsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Ov_chipkaart ov_chipkaart) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, ov_chipkaart.getKaart_nummer());
            statement.setDate(2, ov_chipkaart.getGeldig_tot());
            statement.setInt(3, ov_chipkaart.getKlasse());
            statement.setDouble(4, ov_chipkaart.getSaldo());
            statement.setInt(5, ov_chipkaart.getReiziger().getReizigerId());

            statement.execute();
            statement.close();

            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Ov_chipkaart ov_chipkaart) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ? where kaart_nummer = ?");
            statement.setDate(1, ov_chipkaart.getGeldig_tot());
            statement.setInt(2, ov_chipkaart.getKlasse());
            statement.setDouble(3, ov_chipkaart.getSaldo());
            statement.setInt(4, ov_chipkaart.getKaart_nummer());
            statement.execute();
            statement.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Ov_chipkaart ov_chipkaart) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer = ?");
            statement.setInt(1, ov_chipkaart.getKaart_nummer());
            statement.execute();
            statement.close();

            ov_chipkaart.getReiziger().removeChipKaart(ov_chipkaart);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Ov_chipkaart findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date geldig_tot = resultSet.getDate(2);
                int klasse = resultSet.getInt(3);
                Double saldo = resultSet.getDouble(4);
                int reiziger_id = resultSet.getInt(5);
                return new Ov_chipkaart(id, geldig_tot, klasse, saldo, reizigerDAOsql.findById(reiziger_id));
            }
            statement.close();
            System.out.println("geen chipkaart met dit id");
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from ov_chipkaart where reiziger_id = ?");
            statement.setInt(1, reiziger.getReizigerId());
            ResultSet results = statement.executeQuery();
            List<Ov_chipkaart> ov_chipkaarten = new ArrayList<>();
            while (results.next()) {
                int kaart_nummer = results.getInt(1);
                Date geldig_tot = results.getDate(2);
                int klasse = results.getInt(3);
                Double saldo = results.getDouble(4);
                ov_chipkaarten.add(new Ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger));
            }
            statement.close();
            return ov_chipkaarten;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Ov_chipkaart> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from ov_chipkaart");
            ResultSet results = statement.executeQuery();
            List<Ov_chipkaart> ov_chipkaarten = new ArrayList<>();
            while (results.next()) {
                int kaart_nummer = results.getInt(1);
                Date geldig_tot = results.getDate(2);
                int klasse = results.getInt(3);
                double saldo = results.getDouble(4);
                int reiziger_id = results.getInt(5);
                ov_chipkaarten.add(new Ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reizigerDAOsql.findById(reiziger_id)));
            }
            statement.close();
            return ov_chipkaarten;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void setReizigerDAOsql(ReizigerDAOsql reizigerDAOsql) {
        this.reizigerDAOsql = reizigerDAOsql;
    }
}
