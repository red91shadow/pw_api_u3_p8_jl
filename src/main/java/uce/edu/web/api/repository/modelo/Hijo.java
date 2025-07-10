package uce.edu.web.api.repository.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreType
@Entity
@Table(name = "hijo")
public class Hijo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hijo")
    private Integer id;

    @Column(name = "hijo_nombre") // estandar para una tabla que los primeros cuatro dijistos del nombre sean
                                  // representativos
    private String nombre;

    @Column(name = "hijo_apellido")
    private String apellido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hijo_estudiante")
    private Estudiante estudiante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
