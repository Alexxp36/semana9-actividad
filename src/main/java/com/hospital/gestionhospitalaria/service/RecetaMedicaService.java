package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.RecetaMedica;
import com.hospital.gestionhospitalaria.repository.RecetaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecetaMedicaService {

    @Autowired
    private RecetaMedicaRepository repository;

    public List<RecetaMedica> listar() {
        return repository.findAll();
    }

    public RecetaMedica guardar(RecetaMedica recetaMedica) {
        return repository.save(recetaMedica);
    }

    public RecetaMedica buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<RecetaMedica> buscarPorConsulta(Long idConsulta) {
        return repository.findByConsultaIdConsulta(idConsulta);
    }
}
