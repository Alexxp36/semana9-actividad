package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "diagnostico")
public class Diagnostico {

    @Id
    private String idDiagnostico;

    private String idConsulta;
    private String descripcion;
    private String tipo;

    public enum TipoDiagnostico {
        PRESUNTIVO("Presuntivo"),
        DEFINITIVO("Definitivo");

        private final String descripcion;

        TipoDiagnostico(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
