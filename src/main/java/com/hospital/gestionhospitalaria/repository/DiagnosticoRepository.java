package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Diagnostico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiagnosticoRepository extends MongoRepository<Diagnostico, String> {
    List<Diagnostico> findByIdConsulta(String idConsulta);
}
