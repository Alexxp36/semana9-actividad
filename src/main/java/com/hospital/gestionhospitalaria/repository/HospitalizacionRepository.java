package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Hospitalizacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HospitalizacionRepository extends MongoRepository<Hospitalizacion, String> {
    List<Hospitalizacion> findByIdPaciente(String idPaciente);
    List<Hospitalizacion> findByIdHabitacion(String idHabitacion);
    List<Hospitalizacion> findByEstado(String estado);
}
