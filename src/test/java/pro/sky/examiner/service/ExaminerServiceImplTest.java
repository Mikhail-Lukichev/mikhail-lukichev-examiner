package pro.sky.examiner.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.examiner.model.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions() {
        //Data preparation

        //Expected result preparation
        when(questionService.getSize()).thenReturn(4);
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("test question1", "test answer1"),
                new Question("test question2", "test answer2"),
                new Question("test question3", "test answer3"),
                new Question("test question4", "test answer4")
        );
        List<Question> expectedResult = List.of(
                new Question("test question1", "test answer1"),
                new Question("test question2", "test answer2"),
                new Question("test question3", "test answer3")
        );

        //Test execution
        List<Question> actualResult = examinerService.getQuestions(3);
        assertEquals(expectedResult,actualResult);
    }
}