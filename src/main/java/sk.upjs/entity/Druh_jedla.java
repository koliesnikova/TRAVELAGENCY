package sk.upjs.entity;

import java.util.Objects;

public class Druh_jedla {
    private Long id;
    private String type;

    public Druh_jedla(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Druh_jedla(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (type != null){
            return type;
        } else {
            return id.toString();
        }

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
