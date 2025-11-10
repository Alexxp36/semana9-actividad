package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document(collection = "cita")
public class Cita {
    @Id
    private String idCita;

    private String idPaciente;
    private String idMedico;

    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String estado;

    public enum EstadoCita {
        PROGRAMADA("Programada"),
        ATENDIDA("Atendida"),
        CANCELADA("Cancelada");

        private final String descripcion;

        EstadoCita(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}