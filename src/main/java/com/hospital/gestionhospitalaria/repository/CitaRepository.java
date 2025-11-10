package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Cita;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {
}