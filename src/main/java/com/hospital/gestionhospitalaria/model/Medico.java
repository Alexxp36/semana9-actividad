package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    private String nombres;
    private String apellidos;
    private String colegiatura;
    private String telefono;
    private String correo;
    
    @Enumerated(EnumType.STRING)
    private EstadoMedico estado;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<MedicoEspecialidad> especialidades;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Cita> citas;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
    
    public enum EstadoMedico {
        ACTIVO("Activo"),
        INACTIVO("Inactivo");
        
        private final String descripcion;
        
        EstadoMedico(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
