package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Paciente;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
}