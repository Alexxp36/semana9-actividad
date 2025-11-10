package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}