package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "factura")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;
    
    private Double total;
    
    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
    
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detalles;
    
    public enum EstadoFactura {
        PENDIENTE("Pendiente"),
        PAGADO("Pagado");
        
        private final String descripcion;
        
        EstadoFactura(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
