package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    List<Bitacora> findByUsuarioIdUsuario(Long idUsuario);
    List<Bitacora> findByAccion(String accion);
}
