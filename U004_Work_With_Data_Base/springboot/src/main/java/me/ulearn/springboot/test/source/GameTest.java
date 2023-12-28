package me.ulearn.springboot.test.source;

import me.ulearn.springboot.entity.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private static Game GAME;

    @BeforeEach
    void setUp() {
        GAME = new Game(1L, "1", "1");
    }

    @Test
    public void assertThatGetIdReturnedRightAnswer() {
        assertEquals(1L, GAME.getId());
    }

    @Test
    public void assertThatSetIdReturnedRightAnswer() {
        final long expectedId = 2L;

        GAME.setId(expectedId);

        assertEquals(expectedId, GAME.getId());
    }

    @Test
    public void assertThatGetNameReturnedRightAnswer() {
        assertEquals("1", GAME.getName());
    }

    @Test
    public void assertThatSetNameReturnedRightAnswer() {
        final String expectedName = "Sekiro";

        GAME.setName(expectedName);

        assertEquals(expectedName, GAME.getName());
    }

    @Test
    public void assertThatGetDeveloperReturnedRightAnswer() {
        assertEquals("1", GAME.getDeveloper());
    }

    @Test
    public void assertThatSetDeveloperReturnedRightAnswer() {
        final String expectedDeveloper = "SomeOne";

        GAME.setDeveloper(expectedDeveloper);

        assertEquals(expectedDeveloper, GAME.getDeveloper());
    }
}