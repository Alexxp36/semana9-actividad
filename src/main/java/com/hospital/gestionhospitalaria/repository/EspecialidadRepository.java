package com.hospital.gestionhospitalaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hospital.gestionhospitalaria.model.Especialidad;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository  extends JpaRepository<Especialidad, Long> {
}