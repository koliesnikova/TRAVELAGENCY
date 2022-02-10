package sk.upjs.entity;

import java.util.Date;

public class Type_tour {
    private Long id;
    private String type;

    public Type_tour(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Type_tour() {

    }

    public Type_tour(long id) {
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
        this.id = this.id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
