package com.hospital.gestionhospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bitacora")
public class Bitacora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBitacora;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    private String accion;
    
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    
    private String detalle;
    
    @PrePersist
    protected void onCreate() {
        fechaHora = LocalDateTime.now();
    }
}
