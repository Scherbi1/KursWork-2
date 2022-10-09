package pro.sky.KursWork2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.KursWork2.exception.IncorrectAmountofQuestionException;
import pro.sky.KursWork2.exception.QuestionAlreadyExistsException;
import pro.sky.KursWork2.model.Question;
import pro.sky.KursWork2.service.impl.ExaminerServiceImpl;
import pro.sky.KursWork2.service.impl.JavaQuestionService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    private final static int size = 5;

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;
    private List<Question> questions;

    @BeforeEach
    public void beforEach() {
        Collection<Question> questions = Stream.iterate(1, i -> i + 1)
                .limit(5)
                .map(i -> new Question("Q" + i, "A" + 1))
                .collect(Collectors.toList());

        when(javaQuestionService.getAll())
                .thenReturn(questions);
    }

    @ParameterizedTest
    @MethodSource
    public void getQuestionNegative(int incorrectAmount) {
        assertThatExceptionOfType(IncorrectAmountofQuestionException.class)
                .isThrownBy(() -> examinerServiceImpl.getQuestion(incorrectAmount));
    }
@Test
    public void getQuestionPositive() {
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(
                        questions.get(0),
                        questions.get(0),
                        questions.get(1),
                        questions.get(3),
                        questions.get(4),
                        questions.get(2)
        );
        assertThat(examinerServiceImpl.getQuestion(5)).containsExactly(questions.get(0), questions.get(1), questions.get(3), questions.get(4), questions.get(2));

    }


    public static Stream<Arguments> incorrectAmoiunt() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(size + 100),
                Arguments.of(size + 1)

        );
    }
}
