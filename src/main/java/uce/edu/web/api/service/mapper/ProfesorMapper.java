package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {

    public static ProfesorTo toTo(Profesor profesor) {
        ProfesorTo pTo = new ProfesorTo();
        pTo.setId(profesor.getId());
        pTo.setName(profesor.getName());
        pTo.setLastName(profesor.getLastName());
        pTo.setDepartamento(profesor.getDepartamento());
        pTo.setCargaHoraria(profesor.getCargaHoraria());
        return pTo;
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor p = new Profesor();
        p.setId(profesorTo.getId());
        p.setName(profesorTo.getName());
        p.setLastName(profesorTo.getLastName());
        p.setDepartamento(profesorTo.getDepartamento());
        p.setCargaHoraria(profesorTo.getCargaHoraria());
        return p;
    }

}