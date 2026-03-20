package berserk.ezz.service;

import berserk.ezz.dto.CreateGameRequest;
import berserk.ezz.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

      public Game createGame(CreateGameRequest request);

      public List<Game> findAll();

      public Optional<Game> findById(int id);

      public void restGame(int id);
}
