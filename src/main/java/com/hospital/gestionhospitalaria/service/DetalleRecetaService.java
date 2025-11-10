package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.DetalleReceta;
import com.hospital.gestionhospitalaria.repository.DetalleRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleRecetaService {

    @Autowired
    private DetalleRecetaRepository repository;

    public List<DetalleReceta> listar() {
        return repository.findAll();
    }

    public DetalleReceta guardar(DetalleReceta detalleReceta) {
        return repository.save(detalleReceta);
    }

    public DetalleReceta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<DetalleReceta> buscarPorReceta(Long idReceta) {
        return repository.findByRecetaMedicaIdReceta(idReceta);
    }
}
