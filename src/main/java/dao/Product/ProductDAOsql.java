package dao.Product;

import dao.Ov_chipkaart.Ov_chipkaartDAOsql;
import domein.Ov_chipkaart;
import domein.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ProductDAOsql implements ProductDAO{
    private Connection connection;
    private Ov_chipkaartDAOsql ov_chipkaartDAOsql;

    public ProductDAOsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Product product) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("Insert Into product VALUES (?, ? ,?, ?)");
            preparedStatement.setInt(1, product.getProductNummer());
            preparedStatement.setString(2, product.getNaam());
            preparedStatement.setString(3, product.getBeschrijving());
            preparedStatement.setFloat(4, product.getPrijs());
            preparedStatement.execute();
            preparedStatement.close();

            if (product.getOv_chipkaarten().size() != 0) {
                for (Ov_chipkaart perOvChipkaart : product.getOv_chipkaarten()) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, ?)");
                    statement.setInt(1, perOvChipkaart.getKaart_nummer());
                    statement.setInt(2, product.getProductNummer());
                    statement.setString(3, "actief");
                    statement.setDate(4, Date.valueOf("2000-01-01"));
                    statement.execute();
                    statement.close();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ? where prijs = ?");
            preparedStatement.setInt(1, product.getProductNummer());
            preparedStatement.setString(2, product.getNaam());
            preparedStatement.setString(3, product.getBeschrijving());
            preparedStatement.setFloat(4, product.getPrijs());
            preparedStatement.execute();
            preparedStatement.close();

            if (product.getOv_chipkaarten().size() != 0) {
                for (Ov_chipkaart perOvChipkaart : product.getOv_chipkaarten()) {
                    PreparedStatement statement = connection.prepareStatement("UPDATE ov_chipkaart_product SET kaart_nummer = ?, product_nummer = ?");
                    statement.setInt(1, perOvChipkaart.getKaart_nummer());
                    statement.setInt(2, product.getProductNummer());
                    statement.execute();
                    statement.close();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE product_nummer = ?");
            preparedStatement.setInt(1, product.getProductNummer());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void setOv_chipkaartDAOsql(Ov_chipkaartDAOsql ov_chipkaartDAOsql) {
        this.ov_chipkaartDAOsql = ov_chipkaartDAOsql;
    }
}
