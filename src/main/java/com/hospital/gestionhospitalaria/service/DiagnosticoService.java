package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Diagnostico;
import com.hospital.gestionhospitalaria.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository repository;

    public List<Diagnostico> listar() {
        return repository.findAll();
    }

    public Diagnostico guardar(Diagnostico diagnostico) {
        return repository.save(diagnostico);
    }

    public Diagnostico buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Diagnostico> buscarPorConsulta(Long idConsulta) {
        return repository.findByConsultaIdConsulta(idConsulta);
    }
}
