package pro.sky.examiner.service;

import pro.sky.examiner.model.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
