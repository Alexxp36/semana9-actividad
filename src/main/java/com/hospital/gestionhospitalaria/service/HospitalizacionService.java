package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Hospitalizacion;
import com.hospital.gestionhospitalaria.repository.HospitalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalizacionService {

    @Autowired
    private HospitalizacionRepository repository;

    public List<Hospitalizacion> listar() {
        return repository.findAll();
    }

    public Hospitalizacion guardar(Hospitalizacion hospitalizacion) {
        return repository.save(hospitalizacion);
    }

    public Hospitalizacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Hospitalizacion> buscarPorPaciente(Long idPaciente) {
        return repository.findByPacienteIdPaciente(idPaciente);
    }

    public List<Hospitalizacion> buscarActivas() {
        return repository.findByEstado(Hospitalizacion.EstadoHospitalizacion.ACTIVO);
    }
}
