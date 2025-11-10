package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Medico;
import com.hospital.gestionhospitalaria.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping
    public List<Medico> listar() {
        return service.listar();
    }

    @PostMapping
    public Medico registrar(@RequestBody Medico m) {
        return service.guardar(m);
    }

    @PutMapping("/{id}")
    public Medico actualizar(@PathVariable Long id, @RequestBody Medico m) {
        Medico existente = service.buscarPorId(id);
        if (existente != null) {
            m.setIdMedico(id);
            return service.guardar(m);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
