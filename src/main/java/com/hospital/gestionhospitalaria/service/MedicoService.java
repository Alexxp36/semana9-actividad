package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Medico;
import com.hospital.gestionhospitalaria.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    public List<Medico> listar() {
        return repo.findAll();
    }

    public Medico guardar(Medico m) {
        return repo.save(m);
    }

    public Medico buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
