package sk.upjs.entity;

public class Hotel {
    private Long id;
    private String hotel_name;
    private Integer stars;
    private Type_umiestnenia type_umiestnenia;
    private Float price;
    private String krajina;
    private String mesto;

    public Hotel(String hotel_name, Integer stars, Type_umiestnenia type_umiestnenia, Float price, String krajina, String mesto) {
        this.hotel_name = hotel_name;
        this.stars = stars;
        this.type_umiestnenia = type_umiestnenia;
        this.price = price;
        this.krajina = krajina;
        this.mesto = mesto;
    }

    public Hotel() {

    }

    public Hotel(long l, String dkfl) {
    }

    @Override
    public String toString() {
        if ((hotel_name != null) && (stars != null)  && (price != null) && (krajina != null)&& (mesto != null)){
        return hotel_name ;//+ "  " +
               // stars + " stars " +
                // "    " +
                //price + "$   "
            //+ krajina + "    " +
              //  mesto;
    } else {
        return id.toString();}
    }

    public Hotel(Long id, String hotel_name, Integer stars, Type_umiestnenia type_umiestnenia, Float price, String krajina, String mesto) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.stars = stars;
        this.type_umiestnenia = type_umiestnenia;
        this.price = price;
        this.krajina = krajina;
        this.mesto = mesto;
    }

    public Hotel(long id, String res, int stars, float price, String tr, String gf) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Type_umiestnenia getType_umiestnenia() {
        return type_umiestnenia;
    }

    public void setType_umiestnenia(Type_umiestnenia type_umiestnenia) {
        this.type_umiestnenia = type_umiestnenia;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
}