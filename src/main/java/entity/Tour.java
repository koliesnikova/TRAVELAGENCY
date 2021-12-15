package entity;

import java.util.Date;

public class Tour {
    private Long id;
    private Type_tour type_tour;
    private Date date_begin;
    private Date date_end;
    private Druh_jedla druh_jedla;
    private Integer hotel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type_tour getType_tour() {
        return type_tour;
    }

    public void setType_tour(Type_tour type_tour) {
        this.type_tour = type_tour;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Druh_jedla getDruh_jedla() {
        return druh_jedla;
    }

    public void setDruh_jedla(Druh_jedla druh_jedla) {
        this.druh_jedla = druh_jedla;
    }

    public Integer getHotel() {
        return hotel;
    }

    public void setHotel(Integer hotel) {
        this.hotel = hotel;
    }

    public Tour(Long id, Type_tour type_tour, Date date_begin, Date date_end, Druh_jedla druh_jedla, Integer hotel) {
        this.id = id;
        this.type_tour = type_tour;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.druh_jedla = druh_jedla;
        this.hotel = hotel;
    }
}