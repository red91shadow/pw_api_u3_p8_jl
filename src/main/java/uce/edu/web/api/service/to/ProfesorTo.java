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

    // Constructor vacío (implícito, no es necesario escribirlo, pero es crucial que exista)
    public ProfesorTo() {
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    // Método separado para construir los links, igual que en EstudianteTo
    public void buildURI(UriInfo uriInfo) {
        // Link al recurso de hijos del profesor
        URI todosHijos = uriInfo.getBaseUriBuilder()
                .path(Profesorcontroller.class)
                .path(Profesorcontroller.class, "obtenerHijosPorId")
                .build(this.id);
        _links.put("hijos", todosHijos.toString());

        // Link al propio recurso (self)
        URI self = uriInfo.getBaseUriBuilder()
                .path(Profesorcontroller.class)
                .path(Integer.toString(this.id))
                .build();
        _links.put("self", self.toString());
    }

    // Getters y Setters para todos los campos

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