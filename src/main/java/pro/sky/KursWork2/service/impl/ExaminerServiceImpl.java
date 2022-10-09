package pro.sky.KursWork2.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.SelectTag;
import pro.sky.KursWork2.exception.IncorrectAmountofQuestionException;
import pro.sky.KursWork2.model.Question;
import pro.sky.KursWork2.service.ExaminerService;
import pro.sky.KursWork2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountofQuestionException();
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size()<amount){
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
