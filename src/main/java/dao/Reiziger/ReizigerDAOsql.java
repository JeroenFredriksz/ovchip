package dao.Reiziger;

import domein.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ReizigerDAOsql implements ReizigerDAO {
    private Connection connection;

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

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE From reiziger WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getReizigerId());

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }
}
