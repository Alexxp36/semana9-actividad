package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diagnostico")
public class Diagnostico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiagnostico;
    
    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
    
    private String descripcion;
    
    @Enumerated(EnumType.STRING)
    private TipoDiagnostico tipo;
    
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
