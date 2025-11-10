package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    public List<Paciente> listar() {
        return repo.findAll();
    }

    public Paciente guardar(Paciente p) {
        return repo.save(p);
    }

    public Paciente buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
