package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Hijo;

public interface IHijoRepo {
    public List<Hijo> buscarPorEstudianteId(Integer id);
}
