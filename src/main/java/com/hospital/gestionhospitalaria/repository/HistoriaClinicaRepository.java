package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    List<HistoriaClinica> findByPacienteIdPaciente(Long idPaciente);
}
