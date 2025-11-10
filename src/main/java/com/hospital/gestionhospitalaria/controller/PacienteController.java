package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }

    @PostMapping
    public Paciente registrar(@RequestBody Paciente p) {
        return service.guardar(p);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Long id, @RequestBody Paciente p) {
        Paciente existente = service.buscarPorId(id);
        if (existente != null) {
            p.setIdPaciente(id);
            return service.guardar(p);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
