package pro.sky.examiner.service;

import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.IllegalRequestedQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> getQuestions(int amount) {

        if (amount > questionService.getAll().size()) {
            throw new IllegalRequestedQuestionsException("Requested questions amount is bigger than number of questions");
        }
        List<Question> returnQuestions = new ArrayList<Question>();
        int i = 0;
        Question question;
        while (i < amount) {
            question = questionService.getRandomQuestion();
            if (!returnQuestions.contains(question)){
                returnQuestions.add(question);
                i++;
            }
        }
        return returnQuestions;
    }
}
