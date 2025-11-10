package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
    List<DetalleFactura> findByFacturaIdFactura(Long idFactura);
}
