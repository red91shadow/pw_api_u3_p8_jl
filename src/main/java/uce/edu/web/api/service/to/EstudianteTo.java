package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.Estudiantecontroller;

public class EstudianteTo {

    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;
    private String genero;
    public Map<String, String> _links = new HashMap<>();

    private Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    
    public void buildURI(UriInfo uriInfo) {
       
        URI todosHijos = uriInfo.getBaseUriBuilder().path(Estudiantecontroller.class)
                .path(Estudiantecontroller.class, "obtenerHijosPorId").build(id);

        _links.put("hijos", todosHijos.toString());

        URI self = uriInfo.getBaseUriBuilder().path(Estudiantecontroller.class)
                .path(Integer.toString(this.id)).build();
        _links.put("self", self.toString());
    }

    // SET y GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
