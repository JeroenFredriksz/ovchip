package domein;

import java.sql.Date;

public class Reiziger {
    int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reizigerId = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
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
        return returnstring;
    }
}
