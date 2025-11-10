package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medico_especialidad")
public class MedicoEspecialidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicoEsp;
    
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
}
