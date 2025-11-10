package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "antecedente_medico")
public class AntecedenteMedico {

    @Id
    private String idAntecedente;

    private String idHistoria;
    private String tipo;
    private String descripcion;

    public enum TipoAntecedente {
        ALERGIAS("Alergias"),
        ENFERMEDADES_PREVIAS("Enfermedades Previas"),
        CIRUGIAS("Cirugías"),
        MEDICAMENTOS("Medicamentos en Uso");

        private final String descripcion;

        TipoAntecedente(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
