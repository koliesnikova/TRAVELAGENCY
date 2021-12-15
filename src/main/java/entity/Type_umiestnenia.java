package entity;

public class Type_umiestnenia {
    private Long id;
    private String type_umiest;

    public void setBalkon(Boolean balkon) {
        this.balkon = balkon;
    }

    public void setVyhladiakova_izba(Boolean vyhladiakova_izba) {
        this.vyhladiakova_izba = vyhladiakova_izba;
    }

    private Boolean balkon;

    public Boolean getBalkon() {
        return balkon;
    }

    public Boolean getVyhladiakova_izba() {
        return vyhladiakova_izba;
    }

    private Boolean vyhladiakova_izba;

    public Type_umiestnenia(Long id, String type_umiest, Boolean balkon, Boolean vyhladiakova_izba) {
        this.id = id;
        this.type_umiest = type_umiest;
        this.balkon = balkon;
        this.vyhladiakova_izba = vyhladiakova_izba;
    }


    @Override
    public String toString() {
        return "Type_umiestnenia{" +
                "id=" + id +
                ", type_umiest='" + type_umiest + '\'' +
                ", balkon=" + balkon +
                ", vyhladiakova_izba=" + vyhladiakova_izba +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType_umiest() {
        return type_umiest;
    }

    public void setType_umiest(String type_umiest) {
        this.type_umiest = type_umiest;
    }


}
