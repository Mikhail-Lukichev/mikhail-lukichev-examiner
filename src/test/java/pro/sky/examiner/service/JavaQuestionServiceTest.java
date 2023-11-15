package pro.sky.examiner.service;

import org.junit.jupiter.api.Test;
import pro.sky.examiner.exception.DuplicateQuestionException;
import pro.sky.examiner.exception.NoQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private final QuestionService service = new JavaQuestionService();
    @Test
    void addQuestionAnswer_success() {
        //Data preparation
        service.add("test question","test answer");

        //Expected result preparation
        List<Question> expectedResult = List.of(new Question("test question","test answer"));

        //Test execution
        List<Question> actualResult = service.getAll();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void addQuestionAnswer_withDuplicateQuestionException() {
        //Data preparation
        service.add("test question","test answer");

        //Expected result preparation
        String expectedMessage = "Such question already added";

        //Test execution
        Exception exception = assertThrows(DuplicateQuestionException.class,
                () -> service.add("test question","test answer")
        );
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void addQuestionObject_success() {
        //Data preparation
        service.add(new Question("test question","test answer"));

        //Expected result preparation
        List<Question> expectedResult = List.of(new Question("test question","test answer"));

        //Test execution
        List<Question> actualResult = service.getAll();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void addQuestionObject_withDuplicateQuestionException() {
        //Data preparation
        service.add(new Question("test question","test answer"));

        //Expected result preparation
        String expectedMessage = "Such question already added";

        //Test execution
        Exception exception = assertThrows(DuplicateQuestionException.class,
                () -> service.add(new Question("test question","test answer"))
        );
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void remove_success() {
        //Data preparation
        service.add("test question1","test answer1");
        service.add("test question2","test answer2");

        //Expected result preparation
        List<Question> expectedResult = List.of(new Question("test question1","test answer1"));

        //Test execution
        service.remove(new Question("test question2","test answer2"));
        List<Question> actualResult = service.getAll();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void remove_withNoQuestionsException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "There are no questions";

        //Test execution
        Exception exception = assertThrows(NoQuestionsException.class,
                () -> service.remove(new Question("test question","test answer"))
                );
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void getAll_success() {
        //Data preparation
        service.add("test question1","test answer1");

        //Expected result preparation
        List<Question> expectedResult = List.of(new Question("test question1","test answer1"));

        //Test execution
        List<Question> actualResult = service.getAll();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getAll_withNoQuestionsException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "There are no questions";

        //Test execution
        Exception exception = assertThrows(NoQuestionsException.class,
                () -> service.getAll()
        );
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void getRandomQuestion_success() {
        //Data preparation
        service.add("test question1","test answer1");

        //Expected result preparation
        Question expectedResult = new Question("test question1","test answer1");

        //Test execution
        Question actualResult = service.getRandomQuestion();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getRandomQuestion_withNoQuestionsException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "There are no questions";

        //Test execution
        Exception exception = assertThrows(NoQuestionsException.class,
                () -> service.getRandomQuestion()
        );
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void getSize() {
        //Data preparation
        service.add("test question1","test answer1");
        service.add("test question2","test answer2");
        service.add("test question3","test answer3");

        //Expected result preparation
        int expectedResult = 3;

        //Test execution
        int actualResult = service.getSize();
        assertEquals(expectedResult,actualResult);
    }
}