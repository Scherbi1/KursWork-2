package pro.sky.KursWork2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sky.KursWork2.exception.QuestionAlreadyExistsException;
import pro.sky.KursWork2.exception.QuestionNotFoundException;
import pro.sky.KursWork2.model.Question;
import pro.sky.KursWork2.service.QuestionService;
import pro.sky.KursWork2.service.impl.JavaQuestionService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();


 /*   @AfterEach
    public void afterEach() {
        questionService.getAll().forEach(questionService::remove);
    }*/

    @Test
    public void addTest() {
        assertThat(questionService.getAll().isEmpty());
        Question question = new Question("Вопрос 1 ", " Ответ 1 ");
        Question question2 = new Question("Вопрос 2 ", " Ответ 2 ");

        questionService.add(question);
        assertEquals(1, questionService.getAll().size());
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question.getQuestion(), question.getAnswer()));
        questionService.add(question2.getQuestion(), question2.getAnswer());
       assertEquals(2, questionService.getAll().size());
    }

    @Test
    public void removeTest() {
        assertThat(questionService.getAll().isEmpty());
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.remove(new Question("Вопрос 1 ", "Вопрос 2 ")));

        Question question = add ("Вопрос 1 ", " Ответ 1 ");
        assertEquals(1, questionService.getAll().size());

        assertThatExceptionOfType(QuestionNotFoundException.class).
                isThrownBy(() -> questionService.remove(new Question("Вопрос 1 ", "Вопрос 2 ")));

       questionService.remove(question);
       assertThat(questionService.getAll().isEmpty());
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getAll().isEmpty());
        for (int i =1; i<=6; i++) {
            add("Q" + i, "A" + i);
        }
        assertEquals(6, questionService.getAll().size());
    }

    @Test
    public Question add(String question, String answer) {
        return questionService.add(new Question(question,answer));
    }
}
