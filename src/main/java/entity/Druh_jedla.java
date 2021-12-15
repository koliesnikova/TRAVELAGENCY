package entity;

import java.util.Objects;

public class Druh_jedla {
    private Long id;
    private String type;

    public Druh_jedla(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Druh_jedla{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
