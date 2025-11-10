package com.hospital.gestionhospitalaria.service;


import com.hospital.gestionhospitalaria.model.Especialidad;
import com.hospital.gestionhospitalaria.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository repo;

    public List<Especialidad> listar() {
        return repo.findAll();
    }

    public Especialidad guardar(Especialidad e) {
        return repo.save(e);
    }

    public Especialidad buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
