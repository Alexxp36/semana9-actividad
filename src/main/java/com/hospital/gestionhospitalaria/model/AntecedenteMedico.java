package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "antecedente_medico")
public class AntecedenteMedico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedente;
    
    @ManyToOne
    @JoinColumn(name = "id_historia")
    private HistoriaClinica historiaClinica;
    
    @Enumerated(EnumType.STRING)
    private TipoAntecedente tipo;
    
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
