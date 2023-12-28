package me.ulearn.springboot.controller;

import me.ulearn.springboot.entity.Game;
import me.ulearn.springboot.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//#region Task
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;

        repository.saveAll(List.of(
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
        return repository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Game> getGameById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Game postGame(@RequestBody Game game) {
        return repository.save(game);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Game> putGame(@PathVariable Long id, @RequestBody Game game) {
        return repository.existsById(id)
                ? new ResponseEntity<>(game, HttpStatus.OK)
                : new ResponseEntity<>(postGame(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private void deleteGame(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
//#endregion Task
