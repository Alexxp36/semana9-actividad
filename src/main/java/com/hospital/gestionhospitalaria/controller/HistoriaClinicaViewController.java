package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.HistoriaClinica;
import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.service.HistoriaClinicaService;
import com.hospital.gestionhospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/historias-clinicas")
public class HistoriaClinicaViewController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historiasClinicas", historiaClinicaService.listar());
        model.addAttribute("historiaClinica", new HistoriaClinica());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", false);
        return "historias-clinicas";
    }

    @PostMapping
    public String guardar(@RequestParam("paciente.idPaciente") Long idPaciente,
                          @RequestParam("fechaApertura") String fechaApertura,
                          @RequestParam("observaciones") String observaciones,
                          Model model) {

        try {
            HistoriaClinica historiaClinica = new HistoriaClinica();
            Paciente paciente = pacienteService.buscarPorId(idPaciente);
            
            if (paciente == null) {
                model.addAttribute("error", "Paciente no encontrado");
                model.addAttribute("historiasClinicas", historiaClinicaService.listar());
                model.addAttribute("historiaClinica", new HistoriaClinica());
                model.addAttribute("pacientes", pacienteService.listar());
                model.addAttribute("editMode", false);
                return "historias-clinicas";
            }
            
            historiaClinica.setPaciente(paciente);
            historiaClinica.setFechaApertura(LocalDate.parse(fechaApertura));
            historiaClinica.setObservaciones(observaciones);

            historiaClinicaService.guardar(historiaClinica);
            return "redirect:/historias-clinicas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la historia clínica: " + e.getMessage());
            model.addAttribute("historiasClinicas", historiaClinicaService.listar());
            model.addAttribute("historiaClinica", new HistoriaClinica());
            model.addAttribute("pacientes", pacienteService.listar());
            model.addAttribute("editMode", false);
            return "historias-clinicas";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(id);
        model.addAttribute("historiaClinica", historiaClinica);
        model.addAttribute("historiasClinicas", historiaClinicaService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", true);
        return "historias-clinicas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("paciente.idPaciente") Long idPaciente,
                             @RequestParam("fechaApertura") String fechaApertura,
                             @RequestParam("observaciones") String observaciones,
                             Model model) {

        try {
            HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(id);
            if (historiaClinica != null) {
                Paciente paciente = pacienteService.buscarPorId(idPaciente);
                
                if (paciente == null) {
                    model.addAttribute("error", "Paciente no encontrado");
                    model.addAttribute("historiaClinica", historiaClinica);
                    model.addAttribute("historiasClinicas", historiaClinicaService.listar());
                    model.addAttribute("pacientes", pacienteService.listar());
                    model.addAttribute("editMode", true);
                    return "historias-clinicas";
                }
                
                historiaClinica.setPaciente(paciente);
                historiaClinica.setFechaApertura(LocalDate.parse(fechaApertura));
                historiaClinica.setObservaciones(observaciones);

                historiaClinicaService.guardar(historiaClinica);
            }

            return "redirect:/historias-clinicas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la historia clínica: " + e.getMessage());
            HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(id);
            model.addAttribute("historiaClinica", historiaClinica);
            model.addAttribute("historiasClinicas", historiaClinicaService.listar());
            model.addAttribute("pacientes", pacienteService.listar());
            model.addAttribute("editMode", true);
            return "historias-clinicas";
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        historiaClinicaService.eliminar(id);
        return "redirect:/historias-clinicas";
    }
}
