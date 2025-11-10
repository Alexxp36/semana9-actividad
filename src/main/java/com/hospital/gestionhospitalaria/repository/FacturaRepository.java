package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaRepository extends MongoRepository<Factura, String> {
    List<Factura> findByIdPaciente(String idPaciente);
    List<Factura> findByEstado(String estado);
}
