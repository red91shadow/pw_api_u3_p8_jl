package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.Profesorcontroller;

public class ProfesorTo {

    private Integer id;
    private String name;
    private String lastName;
    private String departamento;
    private Integer cargaHoraria;
    private String genero;
    public Map<String, String> _links = new HashMap<>();

    public ProfesorTo(Integer id, String name, String lastName, String departamento, Integer cargaHoraria,
            String genero, UriInfo uriInfo) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.departamento = departamento;
        this.cargaHoraria = cargaHoraria;
        this.genero = genero;

        URI todosHijos = uriInfo.getBaseUriBuilder().path(Profesorcontroller.class)
                .path(Profesorcontroller.class, "obtenerHijosPorId").build(id);

        _links.put("hijos", todosHijos.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
