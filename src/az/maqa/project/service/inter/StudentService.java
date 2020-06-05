package az.maqa.project.service.inter;

import az.maqa.project.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList() throws Exception;

    boolean add(Student student) throws Exception;

    Student getStudentById(Long id) throws Exception;

    boolean update(Student student, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Student> search(String keyword) throws Exception;


}
