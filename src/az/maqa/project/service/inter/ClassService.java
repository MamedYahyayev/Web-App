package az.maqa.project.service.inter;

import az.maqa.project.model.ClassRoom;

import java.util.List;

public interface ClassService {

    List<ClassRoom> getClassList() throws Exception;

    boolean add(ClassRoom classRoom) throws Exception;

    ClassRoom getClassRoomById(Long id) throws Exception;

    boolean update(ClassRoom classRoom, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<ClassRoom> search(String keyword) throws Exception;

}
