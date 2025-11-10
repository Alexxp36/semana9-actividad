package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Especialidad;
import com.hospital.gestionhospitalaria.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EspecialidadViewController {

    @Autowired
    private EspecialidadService service;

    @GetMapping("/especialidades")
    public String listarEspecialidades(Model model) {
        List<Especialidad> lista = service.listar();
        model.addAttribute("especialidades", lista);
        model.addAttribute("especialidad", new Especialidad());
        return "especialidades";
    }

    @PostMapping("/especialidades")
    public String guardarEspecialidad(@ModelAttribute Especialidad especialidad) {
        service.guardar(especialidad);
        return "redirect:/especialidades";
    }

    @GetMapping("/especialidades/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/especialidades";
    }
}
