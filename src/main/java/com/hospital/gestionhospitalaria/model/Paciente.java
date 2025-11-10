package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.util.Date;

@Data
@Document(collection = "paciente")
public class Paciente {
    @Id
    private String idPaciente;

    private String dni;
    private String nombres;
    private String apellidos;

    @Field("fechaNacimiento")
    private Date fechaNacimiento;

    private String sexo;
    private String direccion;
    private String telefono;
    private String correo;
    private String estado;

    public enum EstadoPaciente {
        ACTIVO("Activo"),
        INACTIVO("Inactivo");

        private final String descripcion;

        EstadoPaciente(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
