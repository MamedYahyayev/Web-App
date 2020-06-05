package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.StudentDao;
import az.maqa.project.model.Student;
import az.maqa.project.service.inter.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public boolean add(Student student) throws Exception {
        return studentDao.add(student);
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        return studentDao.getStudentById(id);
    }

    @Override
    public boolean update(Student student, Long id) throws Exception {
        return studentDao.update(student, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return studentDao.delete(id);
    }

    @Override
    public List<Student> search(String keyword) throws Exception {
        return studentDao.search(keyword);
    }
}
