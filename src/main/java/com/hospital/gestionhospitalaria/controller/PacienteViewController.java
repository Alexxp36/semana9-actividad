package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("editMode", false);
        return "pacientes";
    }

    @PostMapping
    public String guardar(@ModelAttribute Paciente paciente) {
        pacienteService.guardar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return "redirect:/pacientes";
    }

    // 🟢 Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("paciente", paciente);
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", true);
        return "pacientes";
    }

    // 🟢 Guardar cambios
    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id, @ModelAttribute Paciente paciente) {
        paciente.setIdPaciente(id);
        pacienteService.guardar(paciente);
        return "redirect:/pacientes";
    }
}
