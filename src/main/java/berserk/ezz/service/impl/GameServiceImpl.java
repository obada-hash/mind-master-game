package berserk.ezz.service.impl;

import berserk.ezz.dto.CreateGameRequest;
import berserk.ezz.dto.UpdateGameRequest;
import berserk.ezz.entity.Category;
import berserk.ezz.entity.Game;
import berserk.ezz.entity.Question;
import berserk.ezz.repository.GameRepo;
import berserk.ezz.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepo gameRepo;

    @Override
    public Game createGame(CreateGameRequest request) {

        Game game = new Game();
        game.setName(request.getName());
        List<Category> categories = request.getCategories().stream()
                .map(cat -> {
                    Category category = new Category();
                    category.setName(cat.getName());
                    category.setGame(game);

                    List<Question> questions = cat.getQuestions().stream()
                            .map(qn -> {
                                Question question = new Question();
                                question.setDescription(qn.getDescription());
                                question.setAnswer(qn.getAnswer());
                                question.setScore(qn.getScore());
                                question.setCategory(category);
                                return question;
                            }).toList();
                    category.setQuestions(questions);
                    return category;
                }).toList();

        game.setCategories(categories);
        return gameRepo.save(game);
    }

    @Override
    public List<Game> findAll() {
        return gameRepo.findAll();
    }

    @Override
    public Optional<Game> findById(int id) {
        return gameRepo.findById(id);
    }

    @Override
    public void restGame(int id) {
        Game game = gameRepo.findById(id).get();

        game.getCategories().stream()
                .forEach(cat -> cat.getQuestions()
                        .forEach(qn -> qn.setAnswered(false)));

        gameRepo.save(game);
    }

    @Transactional
    @Override
    public Game updateGame(int id, UpdateGameRequest game) {
        Game oldGame = gameRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Game not found")
        );
        if (game.getName() != null) {
            oldGame.setName(game.getName());
        }
        if (game.getCategories() != null) {
            oldGame.getCategories().clear();
            gameRepo.save(oldGame);


            List<Category> categories = game.getCategories().stream()
                    .map((cat) -> {
                                Category category = new Category();
                                category.setName(cat.getName());
                                category.setGame(oldGame);
                                List<Question> questionList = cat.getQuestions().stream()
                                        .map((qu) -> {
                                            Question question = new Question();
                                            question.setDescription(qu.getDescription());
                                            question.setAnswer(qu.getAnswer());
                                            question.setScore(qu.getScore());
                                            question.setAnswered(false);
                                            question.setCategory(category);
                                            return question;
                                        })
                                        .collect(Collectors.toList());
                                category.setQuestions(questionList);
                                return category;
                            }
                    )
                    .collect(Collectors.toList());
            oldGame.getCategories().addAll(categories);
        }
        return gameRepo.save(oldGame);
    }

    @Override
    public void deleteGame(int id) {
        Game game = gameRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Game not found")
        );
        gameRepo.delete(game);
    }
}
