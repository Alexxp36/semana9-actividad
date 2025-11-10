package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    private String dni;
    private String nombres;
    private String apellidos;
    
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    
    private String sexo;
    private String direccion;
    private String telefono;
    private String correo;
    
    @Enumerated(EnumType.STRING)
    private EstadoPaciente estado;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<HistoriaClinica> historiasClinicas;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Hospitalizacion> hospitalizaciones;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Factura> facturas;
    
    public enum EstadoPaciente {
        ACTIVO("Activo"),
        INACTIVO("Inactivo");
        
        private final String descripcion;
        
        EstadoPaciente(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
