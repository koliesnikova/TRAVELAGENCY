package entity;

import java.util.Date;

public class Tour {
    private Long id;
    private Type_tour type_tour;
    private Date date_begin;
    private Date date_end;
    private Druh_jedla druh_jedla;
    private Hotel hotel;

    public Tour(Date date_begin, Date date_end) {
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.hotel = hotel;
    }

    public Tour(Long id, Type_tour type_tour, Date date_begin, Date date_end, Druh_jedla druh_jedla, Hotel hotel) {
        this.id = id;
        this.type_tour = type_tour;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.druh_jedla = druh_jedla;
        this.hotel = hotel;
    }

    public Tour() {

    }

    public Tour(Date date, Date date2, Long id) {
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", type_tour=" + type_tour +
                ", date_begin=" + date_begin +
                ", date_end=" + date_end +
                ", druh_jedla=" + druh_jedla +
                ", hotel=" + hotel +
                '}';
    }

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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}