package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.IllegalRequestedQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;

    private final QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(@Qualifier("JavaQuestionService") QuestionService javaQuestionService, @Qualifier("MathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    public List<Question> getQuestions(int amount) {
        if (amount > (javaQuestionService.getSize() + mathQuestionService.getSize())) {
            throw new IllegalRequestedQuestionsException("Requested questions amount is bigger than number of questions");
        }
        List<Question> returnQuestions = new ArrayList<Question>();

        Random random = new Random();

        //java questions number
        int javaQuestionsNumber = random.nextInt(amount + 1);
        //math questions number
        int mathQuestionsNumber = amount - javaQuestionsNumber;

        //check if java or math question number is not bigger than the number of stored questions
        if (javaQuestionsNumber > javaQuestionService.getSize()) {
            javaQuestionsNumber = javaQuestionService.getSize();
            mathQuestionsNumber = amount - javaQuestionsNumber;
        }
        if (mathQuestionsNumber > mathQuestionService.getSize()) {
            mathQuestionsNumber = mathQuestionService.getSize();
            javaQuestionsNumber = amount - mathQuestionsNumber;
        }

        //fill the return list with java questions
        Question question;
        int i = 0;
        while (i < javaQuestionsNumber) {
            question = javaQuestionService.getRandomQuestion();
            if (!returnQuestions.contains(question)) {
                returnQuestions.add(question);
                i++;
            }
        }

        //fill the return list with math questions
        i = 0;
        while (i < mathQuestionsNumber) {
            question = mathQuestionService.getRandomQuestion();
            if (!returnQuestions.contains(question)) {
                returnQuestions.add(question);
                i++;
            }
        }
        return returnQuestions;
    }
}
