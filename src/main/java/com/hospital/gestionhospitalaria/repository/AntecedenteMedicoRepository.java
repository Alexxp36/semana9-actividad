package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.AntecedenteMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AntecedenteMedicoRepository extends JpaRepository<AntecedenteMedico, Long> {
    List<AntecedenteMedico> findByHistoriaClinicaIdHistoria(Long idHistoria);
}
