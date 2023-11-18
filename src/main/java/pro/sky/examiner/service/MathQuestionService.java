package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.NoQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.List;
import java.util.Random;

@Service
@Component
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public MathQuestionService(@Qualifier("MathQuestionRepository") QuestionRepository questionRepository) {
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
        questionRepository.add("Math Test Question 1", "Math Test Answer 1");
        questionRepository.add("Math Test Question 2", "Math Test Answer 2");
        questionRepository.add("Math Test Question 3", "Math Test Answer 3");
        questionRepository.add("Math Test Question 4", "Math Test Answer 4");
        questionRepository.add("Math Test Question 5", "Math Test Answer 5");
        questionRepository.add("Math Test Question 6", "Math Test Answer 6");
        questionRepository.add("Math Test Question 7", "Math Test Answer 7");
    }

    public int getSize() {
        return questionRepository.getSize();
    }
}
