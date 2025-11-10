package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.DetalleFactura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleFacturaRepository extends MongoRepository<DetalleFactura, String> {
    List<DetalleFactura> findByIdFactura(String idFactura);
}
