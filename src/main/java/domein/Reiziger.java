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
        if (adres == null) {
            return String.format("id: %s, %s %s %s, %s", reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum);

        }
        return String.format("id: %s, %s %s %s, %s\nadresId: %s, %s %s %s", reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum, adres.getAdresId(), adres.getStraat(), adres.getHuisnummer(), adres.getWoonplaats());
    }
}
