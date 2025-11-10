package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultaRepository extends MongoRepository<Consulta, String> {
    List<Consulta> findByIdPaciente(String idPaciente);
    List<Consulta> findByIdMedico(String idMedico);
    List<Consulta> findByIdHistoria(String idHistoria);
}
