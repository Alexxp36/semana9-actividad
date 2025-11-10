package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.DetalleFactura;
import com.hospital.gestionhospitalaria.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository repository;

    public List<DetalleFactura> listar() {
        return repository.findAll();
    }

    public DetalleFactura guardar(DetalleFactura detalleFactura) {
        return repository.save(detalleFactura);
    }

    public DetalleFactura buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<DetalleFactura> buscarPorFactura(Long idFactura) {
        return repository.findByFacturaIdFactura(idFactura);
    }
}
