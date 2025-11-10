package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    List<Diagnostico> findByConsultaIdConsulta(Long idConsulta);
}
