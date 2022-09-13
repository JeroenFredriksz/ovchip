package domein;

public class Adres {
    private Reiziger reiziger;
    private int adresId;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;


    public Adres(Reiziger reiziger, int adresId, String postcode, String huisnummer, String straat, String woonplaats) {
        this.reiziger = reiziger;
        this.adresId = adresId;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;

        reiziger.setAdres(this);
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public int getAdresId() {
        return adresId;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String toString() {
        return reiziger.toString();
    }
}
