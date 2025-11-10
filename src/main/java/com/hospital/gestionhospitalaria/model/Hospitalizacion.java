package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "hospitalizacion")
public class Hospitalizacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHosp;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;
    
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    
    @Column(name = "diagnostico_ingreso")
    private String diagnosticoIngreso;
    
    @Enumerated(EnumType.STRING)
    private EstadoHospitalizacion estado;
    
    public enum EstadoHospitalizacion {
        ACTIVO("Activo"),
        DADO_DE_ALTA("Dado de Alta");
        
        private final String descripcion;
        
        EstadoHospitalizacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
