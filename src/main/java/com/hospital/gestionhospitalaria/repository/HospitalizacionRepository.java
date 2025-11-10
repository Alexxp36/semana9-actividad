package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HospitalizacionRepository extends JpaRepository<Hospitalizacion, Long> {
    List<Hospitalizacion> findByPacienteIdPaciente(Long idPaciente);
    List<Hospitalizacion> findByHabitacionIdHabitacion(Long idHabitacion);
    List<Hospitalizacion> findByEstado(Hospitalizacion.EstadoHospitalizacion estado);
}
