package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.HistoriaClinica;
import com.hospital.gestionhospitalaria.repository.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository repository;

    public List<HistoriaClinica> listar() {
        return repository.findAll();
    }

    public HistoriaClinica guardar(HistoriaClinica historiaClinica) {
        return repository.save(historiaClinica);
    }

    public HistoriaClinica buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<HistoriaClinica> buscarPorPaciente(Long idPaciente) {
        return repository.findByPacienteIdPaciente(idPaciente);
    }
}
