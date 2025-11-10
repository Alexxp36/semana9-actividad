package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(name = "nombre_usuario", unique = true)
    private String nombreUsuario;
    
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Bitacora> bitacoras;
    
    public enum RolUsuario {
        ADMIN("Administrador"),
        MEDICO("Médico"),
        RECEPCIONISTA("Recepcionista"),
        ENFERMERA("Enfermera");
        
        private final String descripcion;
        
        RolUsuario(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
