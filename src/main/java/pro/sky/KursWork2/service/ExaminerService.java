package pro.sky.KursWork2.service;

import pro.sky.KursWork2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
