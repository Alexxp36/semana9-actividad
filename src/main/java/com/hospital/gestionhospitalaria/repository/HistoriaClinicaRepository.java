package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.HistoriaClinica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends MongoRepository<HistoriaClinica, String> {
    List<HistoriaClinica> findByIdPaciente(String idPaciente);
}
