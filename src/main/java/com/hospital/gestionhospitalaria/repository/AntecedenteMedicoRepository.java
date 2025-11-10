package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.AntecedenteMedico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AntecedenteMedicoRepository extends MongoRepository<AntecedenteMedico, String> {
    List<AntecedenteMedico> findByIdHistoria(String idHistoria);
}
