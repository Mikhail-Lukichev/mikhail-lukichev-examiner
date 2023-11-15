package pro.sky.examiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalRequestedQuestionsException extends RuntimeException{
    public IllegalRequestedQuestionsException(String message) {
        super(message);
        System.out.println(message);
    }
}
