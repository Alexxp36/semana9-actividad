package com.hospital.gestionhospitalaria.service;

import com.hospital.gestionhospitalaria.model.Bitacora;
import com.hospital.gestionhospitalaria.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BitacoraService {

    @Autowired
    private BitacoraRepository repository;

    public List<Bitacora> listar() {
        return repository.findAll();
    }

    public Bitacora guardar(Bitacora bitacora) {
        return repository.save(bitacora);
    }

    public Bitacora buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Bitacora> buscarPorUsuario(Long idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Bitacora> buscarPorAccion(String accion) {
        return repository.findByAccion(accion);
    }
}
