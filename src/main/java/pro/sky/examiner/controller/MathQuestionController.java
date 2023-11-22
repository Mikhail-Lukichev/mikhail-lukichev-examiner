package pro.sky.examiner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.examiner.model.Question;
import pro.sky.examiner.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {
    private final QuestionService service;

    @Autowired
    public MathQuestionController(@Qualifier("MathQuestionService") QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping(path = "/add")
    public Question addQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        Question questionObject = new Question(question, answer);
        return service.remove(questionObject);
    }

    @GetMapping(path = "/getRandomQuestion")
    public Question getRandomQuestion() {
        return service.getRandomQuestion();
    }
}
