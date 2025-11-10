package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Cita;
import com.hospital.gestionhospitalaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }

    @PostMapping
    public Cita registrar(@RequestBody Cita c) {
        return service.guardar(c);
    }

    @PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id, @RequestBody Cita c) {
        Cita existente = service.buscarPorId(id);
        if (existente != null) {
            c.setIdCita(id);
            return service.guardar(c);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
