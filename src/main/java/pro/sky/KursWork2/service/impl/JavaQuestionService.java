package pro.sky.KursWork2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.KursWork2.exception.QuestionAlreadyExistsException;
import pro.sky.KursWork2.exception.QuestionNotFoundException;
import pro.sky.KursWork2.model.Question;
import pro.sky.KursWork2.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;

    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random=new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));


    }
}
