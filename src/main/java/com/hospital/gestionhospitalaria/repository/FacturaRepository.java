package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByPacienteIdPaciente(Long idPaciente);
    List<Factura> findByEstado(Factura.EstadoFactura estado);
}
