package domein;

import java.sql.Date;

public class Ov_chipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private float saldo;
    private Reiziger reiziger;

    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klasse, float saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;

        reiziger.addChipkaart(this);
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }
}
