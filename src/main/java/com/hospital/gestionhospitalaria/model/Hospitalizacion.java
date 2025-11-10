package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDate;

@Data
@Document(collection = "hospitalizacion")
public class Hospitalizacion {

    @Id
    private String idHosp;

    private String idPaciente;
    private String idHabitacion;

    @Field("fechaIngreso")
    private LocalDate fechaIngreso;

    @Field("fechaAlta")
    private LocalDate fechaAlta;

    @Field("diagnosticoIngreso")
    private String diagnosticoIngreso;

    private String estado;

    public enum EstadoHospitalizacion {
        ACTIVO("Activo"),
        DADO_DE_ALTA("Dado de Alta");

        private final String descripcion;

        EstadoHospitalizacion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
