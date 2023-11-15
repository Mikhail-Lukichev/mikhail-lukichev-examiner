package pro.sky.examiner.service;

import org.springframework.stereotype.Service;
import pro.sky.examiner.exception.DuplicateQuestionException;
import pro.sky.examiner.exception.NoQuestionsException;
import pro.sky.examiner.model.Question;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class JavaQuestionService implements QuestionService{
    private Set<Question> questions;

    public Question add(String question, String answer){
        if (isNull(questions)) {
            questions = new HashSet<Question>();
        }
        for (Question element : questions) {
            if(Objects.equals(element.getQuestion(), question)) throw new DuplicateQuestionException("Such question already added");
        }

        Question questionObject = new Question(question,answer);
        questions.add(questionObject);
        return questionObject;
    }

    public Question add(Question question){
        if (isNull(questions)) {
            questions = new HashSet<Question>();
        }
        for (Question element : questions) {
            if(Objects.equals(element.getQuestion(), question.getQuestion())) throw new DuplicateQuestionException("Such question already added");
        }
        questions.add(question);
        return question;
    }

    public Question remove(Question question){
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }
        questions.remove(question);
        return question;
    }

    public List<Question> getAll(){
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }
        return questions.stream().collect(Collectors.toList());
    }

    public Question getRandomQuestion(){
        if (isNull(questions)) {
            throw new NoQuestionsException("There are no questions");
        }

        //copy HashSet into temporary array
        Question[] arrayQuestions = new Question[questions.size()];
        questions.toArray(arrayQuestions);

        // generate a random number
        Random random = new Random();

        // this will generate a random number between 0 and
        // HashSet.size - 1
        int randomNumber = random.nextInt(questions.size());

        return arrayQuestions[randomNumber];
    }

    public void addTestQuestions(){
        if (isNull(questions)) {
            questions = new HashSet<Question>();
        }
        this.add("Test Question 1","Test Answer 1");
        this.add("Test Question 2","Test Answer 2");
        this.add("Test Question 3","Test Answer 3");
        this.add("Test Question 4","Test Answer 4");
        this.add("Test Question 5","Test Answer 5");
        this.add("Test Question 6","Test Answer 6");
        this.add("Test Question 7","Test Answer 7");
    }

    public int getSize() {
        return questions.size();
    }
}
