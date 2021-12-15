package entity;

import java.util.Date;

public class Clients {
    private Long id;
    private String meno;
    private String priezvisko;
    private Date dat_nar;
    private String adressa;
    private String cislo;
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", dat_nar=" + dat_nar +
                ", adressa='" + adressa + '\'' +
                ", cislo='" + cislo + '\'' +
                '}';
    }



    public Clients(Long id, String meno, String priezvisko, Date dat_nar, String adressa, String cislo) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.dat_nar = dat_nar;
        this.adressa = adressa;
        this.cislo = cislo;
    }

    public Clients(String meno, String priezvisko, String cislo) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cislo = cislo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }


    public String getAdressa() {
        return adressa;
    }

    public Date getDat_nar() {
        return dat_nar;
    }

    public void setDat_nar(Date dat_nar) {
        this.dat_nar = dat_nar;
    }

    public void setAdressa(String adressa) {
        this.adressa = adressa;
    }


    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }


}
