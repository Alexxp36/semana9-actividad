package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "habitacion")
public class Habitacion {

    @Id
    private String idHabitacion;

    private String numero;
    private String tipo;
    private String estado;

    public enum TipoHabitacion {
        UCI("Unidad de Cuidados Intensivos"),
        GENERAL("Habitación General"),
        EMERGENCIA("Emergencia");

        private final String descripcion;

        TipoHabitacion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    public enum EstadoHabitacion {
        DISPONIBLE("Disponible"),
        OCUPADA("Ocupada"),
        MANTENIMIENTO("Mantenimiento");

        private final String descripcion;

        EstadoHabitacion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
