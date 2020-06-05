package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.QuestionsDao;
import az.maqa.project.model.Questions;
import az.maqa.project.service.inter.QuestionsService;

import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {
    private QuestionsDao questionsDao;

    public QuestionsServiceImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public List<Questions> getQuestionsList() throws Exception {
        return questionsDao.getQuestionsList();
    }

    @Override
    public boolean add(Questions questions) throws Exception {
        return questionsDao.add(questions);
    }

    @Override
    public List<Questions> getQuestionByTopicId(Long id) throws Exception {
        return questionsDao.getQuestionByTopicId(id);
    }

    @Override
    public Questions getQuestionById(Long id) throws Exception {
        return questionsDao.getQuestionById(id);
    }

    @Override
    public boolean update(Questions question, Long id) throws Exception {
        return questionsDao.update(question, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return questionsDao.delete(id);
    }

    @Override
    public List<Questions> search(String keyword) throws Exception {
        return questionsDao.search(keyword);
    }
}
