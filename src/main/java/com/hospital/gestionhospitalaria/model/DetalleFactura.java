package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura;
    
    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;
    
    private String concepto;
    private Double monto;
    
    @Enumerated(EnumType.STRING)
    private TipoConcepto tipoConcepto;
    
    public enum TipoConcepto {
        CONSULTA("Consulta"),
        MEDICAMENTO("Medicamento"),
        PROCEDIMIENTO("Procedimiento"),
        HOSPITALIZACION("Hospitalización"),
        LABORATORIO("Laboratorio");
        
        private final String descripcion;
        
        TipoConcepto(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}
