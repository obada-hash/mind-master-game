package berserk.ezz.service.impl;


import berserk.ezz.entity.Question;
import berserk.ezz.repository.QuestionRepo;
import berserk.ezz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    @Override
    public Question getQuestionById(int id) {
        return questionRepo.findById(id).get();
    }

    @Override
    public void markAnswered(int id) {
        Question question = questionRepo.findById(id).get();
        question.setAnswered(true);
        questionRepo.save(question);
    }
}
