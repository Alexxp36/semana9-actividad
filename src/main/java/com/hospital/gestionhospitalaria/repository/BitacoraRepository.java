package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Bitacora;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BitacoraRepository extends MongoRepository<Bitacora, String> {
    List<Bitacora> findByIdUsuario(String idUsuario);
    List<Bitacora> findByAccion(String accion);
}
