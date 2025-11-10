package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Medico;
import com.hospital.gestionhospitalaria.service.MedicoService;
import com.hospital.gestionhospitalaria.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicos")
public class MedicoViewController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("medico", new Medico());
        return "medicos";
    }

    @PostMapping
    public String guardar(@ModelAttribute Medico medico) {
        medicoService.guardar(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return "redirect:/medicos";
    }

    // 🟢 NUEVO: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Medico medico = medicoService.buscarPorId(id);
        model.addAttribute("medico", medico);
        model.addAttribute("editMode", true); // indica que es edición
        return "medicos";
    }

    // 🟢 NUEVO: Guardar los cambios
    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id, @ModelAttribute Medico medico) {
        medico.setIdMedico(id);
        medicoService.guardar(medico);
        return "redirect:/medicos";
    }
}
