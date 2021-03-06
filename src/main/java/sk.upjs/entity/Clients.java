package sk.upjs.entity;

import java.util.Date;

public class Clients  {
    private Long id;
    private String meno;
    private String priezvisko;
    private Date dat_nar;
    private String adressa;
    private String cislo;

    public Clients(String meno, String priezvisko, Date dat_nar, String adressa, String cislo) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.dat_nar = dat_nar;
        this.adressa = adressa;
        this.cislo = cislo;
    }

    public Clients() {

    }

    public Clients(long id) {
        this.id = id;
    }

    @Override
    public String toString() {

if (meno !=null && priezvisko!=null) {
    return meno + " " +
            priezvisko;
} else
{
    return String.valueOf(id);
}

    }



    public Clients(Long id, String meno, String priezvisko, Date dat_nar, String adressa, String cislo) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.dat_nar = dat_nar;
        this.adressa = adressa;
        this.cislo = cislo;
    }

    public Clients(String meno, String priezvisko) {

        this.meno = meno;
        this.priezvisko = priezvisko;

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
