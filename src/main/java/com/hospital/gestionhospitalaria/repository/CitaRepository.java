package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.gestionhospitalaria.model.Cita;
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
}