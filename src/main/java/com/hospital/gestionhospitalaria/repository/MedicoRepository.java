package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Medico;

@Repository
public interface MedicoRepository extends MongoRepository<Medico, String> {
}