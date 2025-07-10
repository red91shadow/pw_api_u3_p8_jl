package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IHijoRepo;
import uce.edu.web.api.repository.modelo.Hijo;

@ApplicationScoped
public class HijoServiceImpl implements IHijoService {

    @Inject
    private IHijoRepo hijoRepo;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        return this.hijoRepo.buscarPorEstudianteId(id);
    }

}
