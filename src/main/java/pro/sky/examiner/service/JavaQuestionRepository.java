package pro.sky.examiner.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.DuplicateQuestionException;
import pro.sky.examiner.exception.NoQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Component
@Qualifier("JavaQuestionRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questions;

    public Question add(String question, String answer) {
        if (isNull(questions)) {
            questions = new HashSet<Question>();
        }
        for (Question element : questions) {
            if (Objects.equals(element.getQuestion(), question))
                throw new DuplicateQuestionException("Such question already added");
        }

        Question questionObject = new Question(question, answer);
        questions.add(questionObject);
        return questionObject;
    }

    public Question add(Question question) {
        if (isNull(questions)) {
            questions = new HashSet<Question>();
        }
        for (Question element : questions) {
            if (Objects.equals(element.getQuestion(), question.getQuestion()))
                throw new DuplicateQuestionException("Such question already added");
        }
        questions.add(question);
        return question;
    }

    public Question remove(Question question) {
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }
        questions.remove(question);
        return question;
    }

    public List<Question> getAll() {
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }
        return questions.stream().collect(Collectors.toList());
    }

    public int getSize() {
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }
        return questions.size();
    }
}
