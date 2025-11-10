package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByEstado(Habitacion.EstadoHabitacion estado);
    List<Habitacion> findByTipo(Habitacion.TipoHabitacion tipo);
}
