package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Data
@Document(collection = "usuario")
public class Usuario {

    @Id
    private String idUsuario;

    @Field("nombreUsuario")
    private String nombreUsuario;

    private String contrasena;
    private String rol;

    public enum RolUsuario {
        ADMIN("Administrador"),
        MEDICO("Médico"),
        RECEPCIONISTA("Recepcionista"),
        ENFERMERA("Enfermera");

        private final String descripcion;

        RolUsuario(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
