package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.repository.modelo.Hijo;

@ApplicationScoped
public class HijoServiceImpl implements HijoService{

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorEstudianteId'");
    }

}
