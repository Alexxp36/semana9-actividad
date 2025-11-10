package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.hospital.gestionhospitalaria.model.Especialidad;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends MongoRepository<Especialidad, String> {
}