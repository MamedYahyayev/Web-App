package az.maqa.project.service.inter;

import az.maqa.project.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getLessonList() throws Exception;

    boolean add(Lesson lesson) throws Exception;

    Lesson getLessonById(Long id) throws Exception;

    boolean update(Lesson lesson, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Lesson> search(String keyword) throws Exception;


}
