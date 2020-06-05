package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.LessonDao;
import az.maqa.project.model.Lesson;
import az.maqa.project.service.inter.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getLessonList() throws Exception {
        return lessonDao.getLessonList();
    }

    @Override
    public boolean add(Lesson lesson) throws Exception {
        return lessonDao.add(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) throws Exception {
        return lessonDao.getLessonById(id);
    }

    @Override
    public boolean update(Lesson lesson, Long id) throws Exception {
        return lessonDao.update(lesson, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return lessonDao.delete(id);
    }

    @Override
    public List<Lesson> search(String keyword) throws Exception {
        return lessonDao.search(keyword);
    }
}
