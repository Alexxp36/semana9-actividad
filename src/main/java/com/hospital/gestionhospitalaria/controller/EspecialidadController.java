package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Especialidad;
import com.hospital.gestionhospitalaria.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    @GetMapping
    public List<Especialidad> listar() {
        return service.listar();
    }

    @PostMapping
    public Especialidad registrar(@RequestBody Especialidad e) {
        return service.guardar(e);
    }

    @PutMapping("/{id}")
    public Especialidad actualizar(@PathVariable Long id, @RequestBody Especialidad e) {
        Especialidad existente = service.buscarPorId(id);
        if (existente != null) {
            e.setIdEspecialidad(id);
            return service.guardar(e);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
