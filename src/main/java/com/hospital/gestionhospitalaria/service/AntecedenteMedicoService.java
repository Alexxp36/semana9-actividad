package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.AntecedenteMedico;
import com.hospital.gestionhospitalaria.repository.AntecedenteMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AntecedenteMedicoService {

    @Autowired
    private AntecedenteMedicoRepository repository;

    public List<AntecedenteMedico> listar() {
        return repository.findAll();
    }

    public AntecedenteMedico guardar(AntecedenteMedico antecedente) {
        return repository.save(antecedente);
    }

    public AntecedenteMedico buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<AntecedenteMedico> buscarPorHistoria(Long idHistoria) {
        return repository.findByHistoriaClinicaIdHistoria(idHistoria);
    }
}
