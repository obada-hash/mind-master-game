package berserk.ezz.service;

import berserk.ezz.entity.Question;

public interface QuestionService {

    public Question getQuestionById(int id);

    public void markAnswered(int id);
}
