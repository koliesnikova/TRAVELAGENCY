package entity;

public class Type_tour {
    private Long id;
    private String type;

    public Type_tour(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type_tour{" +
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
