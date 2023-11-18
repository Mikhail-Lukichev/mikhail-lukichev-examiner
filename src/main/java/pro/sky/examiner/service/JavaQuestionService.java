package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.NoQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.*;

import static java.util.Objects.isNull;

@Service
@Component
@Qualifier("JavaQuestionService")
public class JavaQuestionService implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public JavaQuestionService(@Qualifier("JavaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    public Question add(Question question) {
        return questionRepository.add(question);
    }

    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    public List<Question> getAll() {
        return questionRepository.getAll();
    }

    public Question getRandomQuestion() {
        if (questionRepository.getSize() == 0) {
            throw new NoQuestionsException("There are no questions");
        }

        List<Question> allQuestions = questionRepository.getAll();

        // generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(questionRepository.getSize());

        return allQuestions.get(randomNumber);
    }

    public void addTestQuestions() {
        questionRepository.add("Java Test Question 1", "Java Test Answer 1");
        questionRepository.add("Java Test Question 2", "Java Test Answer 2");
        questionRepository.add("Java Test Question 3", "Java Test Answer 3");
        questionRepository.add("Java Test Question 4", "Java Test Answer 4");
        questionRepository.add("Java Test Question 5", "Java Test Answer 5");
        questionRepository.add("Java Test Question 6", "Java Test Answer 6");
        questionRepository.add("Java Test Question 7", "Java Test Answer 7");
    }

    public int getSize() {
        return questionRepository.getSize();
    }
}
