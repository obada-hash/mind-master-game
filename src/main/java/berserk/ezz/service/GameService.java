package berserk.ezz.service;

import berserk.ezz.dto.CreateGameRequest;
import berserk.ezz.dto.UpdateGameRequest;
import berserk.ezz.entity.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface GameService {

      Game createGame(CreateGameRequest request);

      List<Game> findAll();

      Optional<Game> findById(int id);

      void restGame(int id);

      Game updateGame(int id, UpdateGameRequest game);

      void deleteGame(int id);

}
