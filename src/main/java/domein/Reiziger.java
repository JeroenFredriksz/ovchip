package domein;

import java.sql.Date;

public class Reiziger {
    int reizigerId;
    String voorletters;
    String tussenvoegsel;
    String achternaam;
    Date geboortedatum;

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reizigerId = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
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

    public String toString() {
        return String.format("id: %s\nvoorletters: %s\ntussenvoegsel: %s\nachternaam: %s\ngeboortedatum: %s", reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum);
    }
}
