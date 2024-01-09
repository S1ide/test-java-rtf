package me.ulearn.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//#region Task
@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String developer;

    public Game() {
    }

    public Game(Long id, String name, String developer) {
        this.id = id;
        this.name = name;
        this.developer = developer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
//#endregion Task
