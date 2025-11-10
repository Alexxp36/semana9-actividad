package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoria;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;
    
    private String observaciones;
    
    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<AntecedenteMedico> antecedentes;
    
    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}
