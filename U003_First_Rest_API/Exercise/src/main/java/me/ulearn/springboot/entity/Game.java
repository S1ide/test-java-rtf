package me.ulearn.springboot.entity;

//#region Task

public class Game {
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

    public void setDeveloper(String publisher) {
        this.developer = publisher;
    }
}
//#endregion Task
