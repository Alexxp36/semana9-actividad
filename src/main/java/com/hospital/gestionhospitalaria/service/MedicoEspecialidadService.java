package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.MedicoEspecialidad;
import com.hospital.gestionhospitalaria.repository.MedicoEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoEspecialidadRepository repository;

    public List<MedicoEspecialidad> listar() {
        return repository.findAll();
    }

    public MedicoEspecialidad guardar(MedicoEspecialidad medicoEspecialidad) {
        return repository.save(medicoEspecialidad);
    }

    public MedicoEspecialidad buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<MedicoEspecialidad> buscarPorMedico(Long idMedico) {
        return repository.findByMedicoIdMedico(idMedico);
    }

    public List<MedicoEspecialidad> buscarPorEspecialidad(Long idEspecialidad) {
        return repository.findByEspecialidadIdEspecialidad(idEspecialidad);
    }

    public boolean existeAsignacion(Long idMedico, Long idEspecialidad) {
        return repository.findByMedicoIdMedicoAndEspecialidadIdEspecialidad(idMedico, idEspecialidad).isPresent();
    }
    
    public boolean existeAsignacion(Long idMedico, Long idEspecialidad, Long excludeId) {
        return repository.findByMedicoIdMedicoAndEspecialidadIdEspecialidadAndIdMedicoEspNot(idMedico, idEspecialidad, excludeId).isPresent();
    }
}
