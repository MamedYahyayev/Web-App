package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.ClassDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.service.inter.ClassService;

import java.util.List;

public class ClassServiceImpl implements ClassService {
    private ClassDao classDao;

    public ClassServiceImpl(ClassDao classDao) {
        this.classDao = classDao;
    }

    @Override
    public List<ClassRoom> getClassList() throws Exception {
        return classDao.getClassList();
    }

    @Override
    public boolean add(ClassRoom classRoom) throws Exception {
        return classDao.add(classRoom);
    }

    @Override
    public ClassRoom getClassRoomById(Long id) throws Exception {
        return classDao.getClassRoomById(id);
    }

    @Override
    public boolean update(ClassRoom classRoom, Long id) throws Exception {
        return classDao.update(classRoom, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return classDao.delete(id);
    }

    @Override
    public List<ClassRoom> search(String keyword) throws Exception {
        return classDao.search(keyword);
    }
}
