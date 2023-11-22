package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.examiner.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices = new ArrayList<>();
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(
            @Qualifier("JavaQuestionService") QuestionService javaQuestionService,
            @Qualifier("MathQuestionService") QuestionService mathQuestionService,
            Random random) {
        this.questionServices.add(javaQuestionService);
        this.questionServices.add(mathQuestionService);
        this.random = random;
    }

    public List<Question> getQuestions(int amount) {
        List<Question> returnQuestions = new ArrayList<>();

        //java questions number
        int javaQuestionsNumber = random.nextInt(amount + 1);

        //math questions number
        int mathQuestionsNumber = amount - javaQuestionsNumber;

        //check if java or math question number is not bigger than the number of stored questions
        if (javaQuestionsNumber > questionServices.get(0).getAll().size()) {
            javaQuestionsNumber = questionServices.get(0).getAll().size();
            mathQuestionsNumber = amount - javaQuestionsNumber;
        }

        //fill the return list with java questions
        Question question;
        int i = 0;
        while (i < javaQuestionsNumber) {
            question = questionServices.get(0).getRandomQuestion();
            if (!returnQuestions.contains(question)) {
                returnQuestions.add(question);
                i++;
            }
        }

        //fill the return list with math questions
        i = 0;
        while (i < mathQuestionsNumber) {
            question = questionServices.get(1).getRandomQuestion();
            if (!returnQuestions.contains(question)) {
                returnQuestions.add(question);
                i++;
            }
        }
        return returnQuestions;
    }
}


