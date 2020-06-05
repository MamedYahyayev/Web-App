package az.maqa.project.service.inter;

import az.maqa.project.model.Informations;

import java.util.List;

public interface InformationsService {
    List<Informations> getInformationsList() throws Exception;

    boolean add(Informations information) throws Exception;

    Informations getInformationById(Long id) throws Exception;

    boolean update(Informations informations, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Informations> search(String keyword) throws Exception;


}
