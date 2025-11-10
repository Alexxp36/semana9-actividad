package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.RecetaMedica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecetaMedicaRepository extends MongoRepository<RecetaMedica, String> {
    List<RecetaMedica> findByIdConsulta(String idConsulta);
}
