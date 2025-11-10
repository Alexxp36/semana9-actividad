package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Habitacion;
import com.hospital.gestionhospitalaria.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    public List<Habitacion> listar() {
        return repository.findAll();
    }

    public Habitacion guardar(Habitacion habitacion) {
        return repository.save(habitacion);
    }

    public Habitacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Habitacion> buscarDisponibles() {
        return repository.findByEstado(Habitacion.EstadoHabitacion.DISPONIBLE);
    }

    public List<Habitacion> buscarPorTipo(Habitacion.TipoHabitacion tipo) {
        return repository.findByTipo(tipo);
    }
}
