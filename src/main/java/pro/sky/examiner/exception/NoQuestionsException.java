package pro.sky.examiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoQuestionsException extends RuntimeException {
    public NoQuestionsException(String message) {
        super(message);
        System.out.println(message);
    }
}
