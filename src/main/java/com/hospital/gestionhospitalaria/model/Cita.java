package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    
    @Enumerated(EnumType.STRING)
    private EstadoCita estado;
    
    public enum EstadoCita {
        PROGRAMADA("Programada"),
        ATENDIDA("Atendida"),
        CANCELADA("Cancelada");
        
        private final String descripcion;
        
        EstadoCita(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}