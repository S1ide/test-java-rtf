package me.ulearn.springboot.controller;

import me.ulearn.springboot.entity.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//#region Task
@RestController
@RequestMapping("/games")
public class GameController {
    private final List<Game> games = new ArrayList<>();

    public GameController() {
        games.addAll(List.of(
                new Game(1L, "Alan Wake 2", "Remedy Entertainment"),
                new Game(2L, "The Witcher 3: Wild Hunt", "CD PROJEKT RED"),
                new Game(3L, "Red Dead Redemption 2", "Rockstar Studios"),
                new Game(4L, "Vampyr", "DONTNOD Entertainment"),
                new Game(5L, "The last of us", "Naughty Dog"),
                new Game(6L, "Cyberpunk 2077", "CD PROJEKT RED"),
                new Game(7L, "Metro Exodus", "4A Games"),
                new Game(8L, "Horizon Zero Dawn", "Guerrilla Games"),
                new Game(9L, "Castle Crashers", "The Behemoth"),
                new Game(10L, "Minecraft", "Mojang Studios")
        ));
    }

    @GetMapping
    private Iterable<Game> getGames() {
        return games;
    }

    @GetMapping("/{id}")
    private Optional<Game> getGameById(@PathVariable Long id) {
        for (Game game : games) {
            if (game.getId().equals(id)) {
                return Optional.of(game);
            }
        }

        return Optional.empty();
    }

    @PostMapping
    private Game postGame(@RequestBody Game game) {
        games.add(game);

        return game;
    }

    @PutMapping("/{id}")
    private ResponseEntity<Game> postGame(@PathVariable Long id, @RequestBody Game game) {
        int gameIndex = -1;

        for (int i = 0; i < games.size(); i++) {
            Game g = games.get(i);

            if (g.getId().equals(id)) {
                games.set(i, game);
                gameIndex = i;
            }
        }

        return gameIndex == -1
                ? new ResponseEntity<>(postGame(game), HttpStatus.CREATED)
                : new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private void deleteGame(@PathVariable Long id) {
        games.removeIf(game -> game.getId().equals(id));
    }
}
//#endregion Task
