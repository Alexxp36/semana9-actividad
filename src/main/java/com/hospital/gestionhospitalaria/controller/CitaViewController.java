package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Cita;
import com.hospital.gestionhospitalaria.model.Medico;
import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.service.CitaService;
import com.hospital.gestionhospitalaria.service.MedicoService;
import com.hospital.gestionhospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/citas")
public class CitaViewController {

    @Autowired
    private CitaService citaService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("cita", new Cita());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", false);
        return "citas";
    }

    @PostMapping
    public String guardar(@RequestParam("fecha") String fecha,
                          @RequestParam("hora") String hora,
                          @RequestParam("motivo") String motivo,
                          @RequestParam("estado") String estado,
                          @RequestParam("medico.idMedico") Long idMedico,
                          @RequestParam("paciente.idPaciente") Long idPaciente,
                          Model model) {

        try {
            Cita cita = new Cita();
            cita.setFecha(LocalDate.parse(fecha));
            cita.setHora(LocalTime.parse(hora));
            cita.setMotivo(motivo);
            cita.setEstado(Cita.EstadoCita.valueOf(estado));

            // Asociar las entidades existentes
            Medico medico = medicoService.buscarPorId(idMedico);
            Paciente paciente = pacienteService.buscarPorId(idPaciente);
            
            if (medico == null || paciente == null) {
                model.addAttribute("error", "Médico o Paciente no encontrado");
                model.addAttribute("citas", citaService.listar());
                model.addAttribute("cita", new Cita());
                model.addAttribute("medicos", medicoService.listar());
                model.addAttribute("pacientes", pacienteService.listar());
                model.addAttribute("editMode", false);
                return "citas";
            }
            
            cita.setMedico(medico);
            cita.setPaciente(paciente);

            citaService.guardar(cita);
            return "redirect:/citas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la cita: " + e.getMessage());
            model.addAttribute("citas", citaService.listar());
            model.addAttribute("cita", new Cita());
            model.addAttribute("medicos", medicoService.listar());
            model.addAttribute("pacientes", pacienteService.listar());
            model.addAttribute("editMode", false);
            return "citas";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Cita cita = citaService.buscarPorId(id);
        model.addAttribute("cita", cita);
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", true);
        return "citas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("fecha") String fecha,
                             @RequestParam("hora") String hora,
                             @RequestParam("motivo") String motivo,
                             @RequestParam("estado") String estado,
                             @RequestParam("medico.idMedico") Long idMedico,
                             @RequestParam("paciente.idPaciente") Long idPaciente,
                             Model model) {

        try {
        Cita cita = citaService.buscarPorId(id);
        if (cita != null) {
            cita.setFecha(LocalDate.parse(fecha));
            cita.setHora(LocalTime.parse(hora));
            cita.setMotivo(motivo);
            cita.setEstado(Cita.EstadoCita.valueOf(estado));

                Medico medico = medicoService.buscarPorId(idMedico);
                Paciente paciente = pacienteService.buscarPorId(idPaciente);
                
                if (medico == null || paciente == null) {
                    model.addAttribute("error", "Médico o Paciente no encontrado");
                    model.addAttribute("cita", cita);
                    model.addAttribute("citas", citaService.listar());
                    model.addAttribute("medicos", medicoService.listar());
                    model.addAttribute("pacientes", pacienteService.listar());
                    model.addAttribute("editMode", true);
                    return "citas";
                }
                
                cita.setMedico(medico);
                cita.setPaciente(paciente);

                citaService.guardar(cita);
            } else {
                model.addAttribute("error", "Cita no encontrada");
                model.addAttribute("citas", citaService.listar());
                model.addAttribute("cita", new Cita());
                model.addAttribute("medicos", medicoService.listar());
                model.addAttribute("pacientes", pacienteService.listar());
                model.addAttribute("editMode", false);
                return "citas";
            }

            return "redirect:/citas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la cita: " + e.getMessage());
            Cita cita = citaService.buscarPorId(id);
            model.addAttribute("cita", cita);
            model.addAttribute("citas", citaService.listar());
            model.addAttribute("medicos", medicoService.listar());
            model.addAttribute("pacientes", pacienteService.listar());
            model.addAttribute("editMode", true);
            return "citas";
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return "redirect:/citas";
    }
}
