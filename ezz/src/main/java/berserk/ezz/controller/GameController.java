package berserk.ezz.controller;

import berserk.ezz.dto.CreateGameRequest;
import berserk.ezz.dto.UpdateGameRequest;
import berserk.ezz.entity.Game;
import berserk.ezz.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody CreateGameRequest request) {
        return new ResponseEntity<>(gameService.createGame(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Game>> getGame(@PathVariable int id) {
        return new ResponseEntity<>(gameService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/reset")
    public ResponseEntity<Void> resetGame(@PathVariable int id) {
        gameService.restGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(
            @PathVariable int id,
            @RequestBody UpdateGameRequest game) {
        return new ResponseEntity<>(gameService.updateGame(id, game), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
