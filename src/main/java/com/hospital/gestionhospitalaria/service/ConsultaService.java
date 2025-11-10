package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Consulta;
import com.hospital.gestionhospitalaria.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    public List<Consulta> listar() {
        return repository.findAll();
    }

    public Consulta guardar(Consulta consulta) {
        return repository.save(consulta);
    }

    public Consulta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Consulta> buscarPorPaciente(Long idPaciente) {
        return repository.findByPacienteIdPaciente(idPaciente);
    }

    public List<Consulta> buscarPorMedico(Long idMedico) {
        return repository.findByMedicoIdMedico(idMedico);
    }
}
