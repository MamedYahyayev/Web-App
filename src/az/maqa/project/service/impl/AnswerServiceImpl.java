package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.AnswerDao;
import az.maqa.project.model.Answer;
import az.maqa.project.service.inter.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerDao answerDao;

    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    public List<Answer> getAnswerList() throws Exception {
        return answerDao.getAnswerList();
    }

    @Override
    public boolean add(Answer answer) throws Exception {
        return answerDao.add(answer);
    }

    @Override
    public Answer getAnswerById(Long id) throws Exception {
        return answerDao.getAnswerById(id);
    }

    @Override
    public boolean update(Answer answer, Long id) throws Exception {
        return answerDao.update(answer, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return answerDao.delete(id);
    }

    @Override
    public List<Answer> search(String keyword) throws Exception {
        return answerDao.search(keyword);
    }
}
