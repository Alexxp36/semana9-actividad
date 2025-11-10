package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "receta_medica")
public class RecetaMedica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;
    
    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
    
    private String indicaciones;
    
    @OneToMany(mappedBy = "recetaMedica", cascade = CascadeType.ALL)
    private List<DetalleReceta> detalles;
}
