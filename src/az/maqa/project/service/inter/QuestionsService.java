package az.maqa.project.service.inter;

import az.maqa.project.model.Questions;

import java.util.List;

public interface QuestionsService {
    List<Questions> getQuestionsList() throws Exception;

    boolean add(Questions questions) throws Exception;

    List<Questions> getQuestionByTopicId(Long id) throws Exception;

    Questions getQuestionById(Long id) throws Exception;

    boolean update(Questions question, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Questions> search(String keyword) throws Exception;

}
