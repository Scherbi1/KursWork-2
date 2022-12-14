package pro.sky.KursWork2.service;

import pro.sky.KursWork2.model.Question;

import java.util.Collection;
import java.util.Collections;

public interface QuestionService {

    Question add (String question, String answer);

    Question add (Question question);

    Question remove (Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
