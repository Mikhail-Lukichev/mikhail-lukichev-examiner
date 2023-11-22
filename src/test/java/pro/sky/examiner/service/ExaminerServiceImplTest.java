package pro.sky.examiner.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.examiner.model.Question;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final QuestionService javaQuestionService = mock(QuestionService.class);
    private final QuestionService mathQuestionService = mock(QuestionService.class);
    private final Random random = mock(Random.class);
    private final ExaminerService examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService, random);

    @Test
    void getQuestions() {
        //Data preparation
        when(random.nextInt(anyInt())).thenReturn(2);
        when(javaQuestionService.getAll()).thenReturn(List.of(
                new Question("java test question1", "java test answer1"),
                new Question("java test question2", "java test answer2"),
                new Question("java test question3", "java test answer3"),
                new Question("java test question4", "java test answer4")
        ));
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("java test question1", "java test answer1"),
                new Question("java test question2", "java test answer2"),
                new Question("java test question3", "java test answer3"),
                new Question("java test question4", "java test answer4")
        );
        when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("math test question1", "math test answer1"),
                new Question("math test question2", "math test answer2"),
                new Question("math test question3", "math test answer3"),
                new Question("math test question4", "math test answer4")
        );

        //Expected result preparation
        List<Question> expectedResult = List.of(
                new Question("java test question1", "java test answer1"),
                new Question("java test question2", "java test answer2"),
                new Question("math test question1", "math test answer1"),
                new Question("math test question2", "math test answer2"),
                new Question("math test question3", "math test answer3")
        );

        //Test execution
        List<Question> actualResult = examinerService.getQuestions(5);
        assertEquals(expectedResult, actualResult);
    }
}