package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.InformationsDao;
import az.maqa.project.model.Informations;
import az.maqa.project.service.inter.InformationsService;

import java.util.List;

public class InformationsServiceImpl implements InformationsService {
    private InformationsDao informationsDao;

    public InformationsServiceImpl(InformationsDao informationsDao) {
        this.informationsDao = informationsDao;
    }

    @Override
    public List<Informations> getInformationsList() throws Exception {
        return informationsDao.getInformationsList();
    }

    @Override
    public boolean add(Informations information) throws Exception {
        return informationsDao.add(information);
    }

    @Override
    public Informations getInformationById(Long id) throws Exception {
        return informationsDao.getInformationById(id);
    }

    @Override
    public boolean update(Informations informations, Long id) throws Exception {
        return informationsDao.update(informations, id);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return informationsDao.delete(id);
    }

    @Override
    public List<Informations> search(String keyword) throws Exception {
        return informationsDao.search(keyword);
    }
}
