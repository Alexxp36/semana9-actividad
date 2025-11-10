package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Cita;
import com.hospital.gestionhospitalaria.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repo;

    public List<Cita> listar() {
        return repo.findAll();
    }

    public Cita guardar(Cita c) {
        return repo.save(c);
    }

    public Cita buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
