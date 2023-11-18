package pro.sky.examiner.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.examiner.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService javaQuestionService;

    @Mock
    private QuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions() {
        //Data preparation

        //Expected result preparation
        when(javaQuestionService.getSize()).thenReturn(4);
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("java test question1", "java test answer1"),
                new Question("java test question2", "java test answer2"),
                new Question("java test question3", "java test answer3"),
                new Question("java test question4", "java test answer4")
        );
        when(mathQuestionService.getSize()).thenReturn(4);
        when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("math test question1", "math test answer1"),
                new Question("math test question2", "math test answer2"),
                new Question("math test question3", "math test answer3"),
                new Question("math test question4", "math test answer4")
        );

        List<Question> expectedResult = List.of(
                new Question("java test question1", "java test answer1"),
                new Question("java test question2", "java test answer2"),
                new Question("math test question1", "math test answer1"),
                new Question("math test question2", "math test answer2"),
                new Question("math test question3", "math test answer3")
        );

        //Test execution
        List<Question> actualResult = examinerService.getQuestions(2);
        assertEquals(expectedResult,actualResult);
    }
}