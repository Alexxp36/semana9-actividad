package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;
    
    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
    
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_historia")
    private HistoriaClinica historiaClinica;
    
    private LocalDate fecha;
    private LocalTime hora;
    
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    
    private String observaciones;
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<RecetaMedica> recetas;
}
