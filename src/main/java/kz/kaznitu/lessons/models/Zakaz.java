package kz.kaznitu.lessons.models;

import javax.persistence.*;

@Entity
public class Zakaz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Integer time;

    public Zakaz() {
    }

    public Zakaz(String name, Integer time) {
        this.name =name;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}