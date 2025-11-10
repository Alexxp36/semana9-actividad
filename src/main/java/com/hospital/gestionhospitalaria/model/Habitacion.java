package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "habitacion")
public class Habitacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    
    private String numero;
    
    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipo;
    
    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;
    
    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Hospitalizacion> hospitalizaciones;
    
    public enum TipoHabitacion {
        UCI("Unidad de Cuidados Intensivos"),
        GENERAL("Habitación General"),
        EMERGENCIA("Emergencia");
        
        private final String descripcion;
        
        TipoHabitacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    public enum EstadoHabitacion {
        DISPONIBLE("Disponible"),
        OCUPADA("Ocupada"),
        MANTENIMIENTO("Mantenimiento");
        
        private final String descripcion;
        
        EstadoHabitacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
