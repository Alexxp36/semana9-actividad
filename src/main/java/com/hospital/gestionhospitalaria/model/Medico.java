package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "medico")
public class Medico {

    @Id
    private String idMedico;

    private String nombres;
    private String apellidos;
    private String colegiatura;
    private String telefono;
    private String correo;
    private String estado;

    public enum EstadoMedico {
        ACTIVO("Activo"),
        INACTIVO("Inactivo");

        private final String descripcion;

        EstadoMedico(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
