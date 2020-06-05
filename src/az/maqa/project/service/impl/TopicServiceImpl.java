package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.TopicDao;
import az.maqa.project.model.Topic;
import az.maqa.project.service.inter.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;

    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public List<Topic> getTopicList() throws Exception {
        return topicDao.getTopicList();
    }

    @Override
    public boolean add(Topic topic) throws Exception {
        return topicDao.add(topic);
    }

    @Override
    public Topic getTopicById(Long id) throws Exception {
        return topicDao.getTopicById(id);
    }

    @Override
    public boolean update(Topic topic, Long id) throws Exception {
        return topicDao.update(topic, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return topicDao.delete(id);
    }

    @Override
    public List<Topic> search(String keyword) throws Exception {
        return topicDao.search(keyword);
    }
}
