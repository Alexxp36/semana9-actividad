package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_receta")
public class DetalleReceta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReceta;
    
    @ManyToOne
    @JoinColumn(name = "id_receta")
    private RecetaMedica recetaMedica;
    
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private String duracion;
}
