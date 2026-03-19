package berserk.ezz.controller;


import berserk.ezz.entity.Question;
import berserk.ezz.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {

    private QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
          return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/answered")
    public ResponseEntity<Void> answerQuestion(@PathVariable int id) {
            questionService.markAnswered(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
