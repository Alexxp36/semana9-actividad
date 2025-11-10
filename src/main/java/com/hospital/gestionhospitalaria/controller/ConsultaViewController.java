package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.*;
import com.hospital.gestionhospitalaria.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/consultas")
public class ConsultaViewController {

    @Autowired
    private ConsultaService consultaService;
    
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", consultaService.listar());
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("historiasClinicas", historiaClinicaService.listar());
        model.addAttribute("editMode", false);
        return "consultas";
    }

    @PostMapping
    public String guardar(@RequestParam(value = "cita.idCita", required = false) Long idCita,
                          @RequestParam("medico.idMedico") Long idMedico,
                          @RequestParam("paciente.idPaciente") Long idPaciente,
                          @RequestParam(value = "historiaClinica.idHistoria", required = false) Long idHistoria,
                          @RequestParam("fecha") String fecha,
                          @RequestParam("hora") String hora,
                          @RequestParam("motivoConsulta") String motivoConsulta,
                          @RequestParam("observaciones") String observaciones,
                          Model model) {

        try {
            Consulta consulta = new Consulta();
            
            if (idCita != null) {
                Cita cita = citaService.buscarPorId(idCita);
                consulta.setCita(cita);
            }
            
            Medico medico = medicoService.buscarPorId(idMedico);
            Paciente paciente = pacienteService.buscarPorId(idPaciente);
            
            if (medico == null || paciente == null) {
                model.addAttribute("error", "Médico o Paciente no encontrado");
                return listar(model);
            }
            
            consulta.setMedico(medico);
            consulta.setPaciente(paciente);
            
            if (idHistoria != null) {
                HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(idHistoria);
                consulta.setHistoriaClinica(historiaClinica);
            }
            
            consulta.setFecha(LocalDate.parse(fecha));
            consulta.setHora(LocalTime.parse(hora));
            consulta.setMotivoConsulta(motivoConsulta);
            consulta.setObservaciones(observaciones);

            consultaService.guardar(consulta);
            return "redirect:/consultas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la consulta: " + e.getMessage());
            return listar(model);
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("consultas", consultaService.listar());
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("historiasClinicas", historiaClinicaService.listar());
        model.addAttribute("editMode", true);
        return "consultas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam(value = "cita.idCita", required = false) Long idCita,
                             @RequestParam("medico.idMedico") Long idMedico,
                             @RequestParam("paciente.idPaciente") Long idPaciente,
                             @RequestParam(value = "historiaClinica.idHistoria", required = false) Long idHistoria,
                             @RequestParam("fecha") String fecha,
                             @RequestParam("hora") String hora,
                             @RequestParam("motivoConsulta") String motivoConsulta,
                             @RequestParam("observaciones") String observaciones,
                             Model model) {

        try {
            Consulta consulta = consultaService.buscarPorId(id);
            if (consulta != null) {
                if (idCita != null) {
                    Cita cita = citaService.buscarPorId(idCita);
                    consulta.setCita(cita);
                }
                
                Medico medico = medicoService.buscarPorId(idMedico);
                Paciente paciente = pacienteService.buscarPorId(idPaciente);
                
                if (medico == null || paciente == null) {
                    model.addAttribute("error", "Médico o Paciente no encontrado");
                    return editar(id, model);
                }
                
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                
                if (idHistoria != null) {
                    HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(idHistoria);
                    consulta.setHistoriaClinica(historiaClinica);
                }
                
                consulta.setFecha(LocalDate.parse(fecha));
                consulta.setHora(LocalTime.parse(hora));
                consulta.setMotivoConsulta(motivoConsulta);
                consulta.setObservaciones(observaciones);

                consultaService.guardar(consulta);
            }

            return "redirect:/consultas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la consulta: " + e.getMessage());
            return editar(id, model);
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        consultaService.eliminar(id);
        return "redirect:/consultas";
    }
}
