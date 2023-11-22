package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.MethodNotAllowedException;
import pro.sky.examiner.model.Question;

import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

@Service
@Component
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService {

    private final Random random;

    @Autowired
    public MathQuestionService(Random random) {
        this.random = random;
    }

    public Question add(String question, String answer) {
        throw new MethodNotAllowedException("Method not allowed");
    }

    public Question add(Question question) {
        throw new MethodNotAllowedException("Method not allowed");
    }

    public Question remove(Question question) {
        throw new MethodNotAllowedException("Method not allowed");
    }

    public List<Question> getAll() {
        throw new MethodNotAllowedException("Method not allowed");
    }

    public Question getRandomQuestion() {
        //define max value of operands
        int maxValue = (int)floor(sqrt(Integer.MAX_VALUE));

        //generate operands a and b
        int a = random.nextInt(maxValue);
        int b = random.nextInt(maxValue);

        //generate a random math operator
        int mathOperator = random.nextInt(4);

        String answer = "";
        String operator = "";

        switch (mathOperator) {
            case 0:
                operator = "+";
                answer = String.valueOf(a + b);
                break;
            case 1:
                operator = "-";
                answer = String.valueOf(a - b);
                break;
            case 2:
                operator = "*";
                answer = String.valueOf(a * b);
                break;
            case 3:
                operator = "/";
                answer = String.valueOf((float) a / b);
                break;
        }

        String question = a + " " + operator + " " + b + "?";
        return new Question(question,answer);
    }
}
