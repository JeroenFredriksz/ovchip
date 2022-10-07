package domein;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private List<Ov_chipkaart> ov_chipkaarten = new ArrayList<>();
    int productNummer;
    String naam;
    String beschrijving;
    float prijs;

    public Product(int productNummer, String naam, String beschrijving, float prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public void addOvChipkaart(Ov_chipkaart ov_chipkaart) {
        ov_chipkaarten.add(ov_chipkaart);
    }

    public void removeOvChipKaart(Ov_chipkaart ov_chipkaart) {
        ov_chipkaarten.remove(ov_chipkaart);
    }

    public List<Ov_chipkaart> getOv_chipkaarten() {
        return ov_chipkaarten;
    }
}
