package me.ulearn.springboot.test.source;

import me.ulearn.springboot.entity.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getId() {
        Game game = new Game(1L,"1","1");
        assertEquals(game.getId(), 1L);
    }

    @Test
    void setId() {
        Game game = new Game();
        game.setId(2L);
        assertEquals(game.getId(), 2L);
    }

    @Test
    void getName() {
        Game game = new Game(3L,"DOKA 2","ME");
        assertEquals(game.getName(), "DOKA 2");
    }

    @Test
    void setName() {
        Game game = new Game();
        game.setName("Sekiro");
        assertEquals(game.getName(), "Sekiro");
    }

    @Test
    void getDeveloper() {
        Game game = new Game(3L,"DOKA 2","ME");
        assertEquals(game.getDeveloper(), "ME");
    }

    @Test
    void setDeveloper() {
        Game game = new Game(3L,"DOKA 2","ME");
        game.setDeveloper("SomeOne");
        assertEquals(game.getDeveloper(), "SomeOne");
    }
}