package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.MedicoEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {
    List<MedicoEspecialidad> findByMedicoIdMedico(Long idMedico);
    List<MedicoEspecialidad> findByEspecialidadIdEspecialidad(Long idEspecialidad);
    Optional<MedicoEspecialidad> findByMedicoIdMedicoAndEspecialidadIdEspecialidad(Long idMedico, Long idEspecialidad);
    Optional<MedicoEspecialidad> findByMedicoIdMedicoAndEspecialidadIdEspecialidadAndIdMedicoEspNot(Long idMedico, Long idEspecialidad, Long excludeId);
}
