package pro.sky.examiner.service;

import org.junit.jupiter.api.Test;
import pro.sky.examiner.exception.MethodNotAllowedException;
import pro.sky.examiner.model.Question;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MathQuestionServiceTest {

    private final Random random = mock(Random.class);
    private final QuestionService service = new MathQuestionService(random);

    @Test
    void addQuestionAnswer_withMethodNotAllowedException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "Method not allowed";

        //Test execution
        Exception exception = assertThrows(MethodNotAllowedException.class,
                () -> service.add("test question", "test answer")
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addQuestionObject_withMethodNotAllowedException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "Method not allowed";

        //Test execution
        Exception exception = assertThrows(MethodNotAllowedException.class,
                () -> service.add(new Question("test question", "test answer"))
        );
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void remove_withMethodNotAllowedException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "Method not allowed";

        //Test execution
        Exception exception = assertThrows(MethodNotAllowedException.class,
                () -> service.remove(new Question("test question", "test answer"))
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll_withMethodNotAllowedException() {
        //Data preparation

        //Expected result preparation
        String expectedMessage = "Method not allowed";

        //Test execution
        Exception exception = assertThrows(MethodNotAllowedException.class,
                service::getAll
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getRandomQuestion_success() {
        //Data preparation
        when(random.nextInt(anyInt())).thenReturn(10).thenReturn(20).thenReturn(0);

        //Expected result preparation
        Question expectedResult = new Question("10 + 20?", "30");

        //Test execution
        Question actualResult = service.getRandomQuestion();
        assertEquals(expectedResult, actualResult);
    }
}