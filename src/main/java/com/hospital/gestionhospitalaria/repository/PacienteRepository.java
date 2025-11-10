package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}