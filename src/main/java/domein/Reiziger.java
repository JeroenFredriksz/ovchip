package domein;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    private List<Ov_chipkaart> chipkaarten;

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reizigerId = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        chipkaarten = new ArrayList<>();
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public Adres getAdres () {
        return adres;
    }

    public String toString() {
        String convertedTussenVoegsel;
        if (tussenvoegsel == null) {
            convertedTussenVoegsel = "";
        }
        else {
            convertedTussenVoegsel = String.format(" %s ", tussenvoegsel);
        }
        String returnstring = String.format("reiziger id: %s, %s %s %s, %s", reizigerId, voorletters, convertedTussenVoegsel, achternaam, geboortedatum);
        if (adres != null) {
            returnstring += "\n" + adres;
        }
        if (chipkaarten.size() != 0) {
            for (Ov_chipkaart perChipkaart : chipkaarten) {
                returnstring += "\n" + perChipkaart;
            }
        }
        return returnstring;
    }

    public void removeChipKaart (Ov_chipkaart ov_chipkaart) {
        chipkaarten.remove(ov_chipkaart);
    }

    public void addChipkaart (Ov_chipkaart ov_chipkaart) {
        chipkaarten.add(ov_chipkaart);
    }

    public List<Ov_chipkaart> getChipkaarten() {
        return chipkaarten;
    }
}
