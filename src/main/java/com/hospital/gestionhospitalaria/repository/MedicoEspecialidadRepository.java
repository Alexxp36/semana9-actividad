package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.MedicoEspecialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoEspecialidadRepository extends MongoRepository<MedicoEspecialidad, String> {
    List<MedicoEspecialidad> findByIdMedico(String idMedico);
    List<MedicoEspecialidad> findByIdEspecialidad(String idEspecialidad);
    Optional<MedicoEspecialidad> findByIdMedicoAndIdEspecialidad(String idMedico, String idEspecialidad);
    Optional<MedicoEspecialidad> findByIdMedicoAndIdEspecialidadAndIdMedicoEspNot(String idMedico, String idEspecialidad, String excludeId);
}
