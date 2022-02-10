package sk.upjs.entity;

import java.util.Date;

public class Predaj {
    private Long id;
    private Clients  client;
    private Date date ;
    private Float price;
    private Tour tour;

    public Clients getClient() {
        return client;
    }

    public Predaj(Long id, Clients client, Date date, Float price, Tour tour) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.price = price;
        this.tour = tour;
    }

    public Predaj() {

    }


    @Override
    public String toString() {
        return "Predaj{" +
                "id=" + id +
                ", client=" + client +
                ", date=" + date +
                ", price=" + price +
                ", tour=" + tour +
                '}';
    }

    public Predaj(Clients client, Date date, Float price, Tour tour) {
        this.client = client;
        this.date = date;
        this.price = price;
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


}