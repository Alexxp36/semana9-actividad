package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.DetalleReceta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleRecetaRepository extends MongoRepository<DetalleReceta, String> {
    List<DetalleReceta> findByIdReceta(String idReceta);
}
