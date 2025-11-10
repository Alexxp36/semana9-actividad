package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Factura;
import com.hospital.gestionhospitalaria.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    public List<Factura> listar() {
        return repository.findAll();
    }

    public Factura guardar(Factura factura) {
        return repository.save(factura);
    }

    public Factura buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Factura> buscarPorPaciente(Long idPaciente) {
        return repository.findByPacienteIdPaciente(idPaciente);
    }

    public List<Factura> buscarPendientes() {
        return repository.findByEstado(Factura.EstadoFactura.PENDIENTE);
    }
}
