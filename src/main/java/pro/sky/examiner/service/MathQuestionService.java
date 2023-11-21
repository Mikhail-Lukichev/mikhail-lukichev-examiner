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

    private final QuestionRepository questionRepository;
    private final Random random;

    @Autowired
    public MathQuestionService(@Qualifier("MathQuestionRepository") QuestionRepository questionRepository, Random random) {
        this.questionRepository = questionRepository;
        this.random = random;
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
        if (questionRepository.getAll().isEmpty()) {
            throw new NoQuestionsException("There are no questions");
        }

        List<Question> allQuestions = questionRepository.getAll();

        // generate a random number
        int randomNumber = random.nextInt(questionRepository.getAll().size());

        return allQuestions.get(randomNumber);
    }
}
