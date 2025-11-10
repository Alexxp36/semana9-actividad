package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.*;
import com.hospital.gestionhospitalaria.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/hospitalizaciones")
public class HospitalizacionViewController {

    @Autowired
    private HospitalizacionService hospitalizacionService;
    
    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long habitacionId, Model model) {
        model.addAttribute("hospitalizaciones", hospitalizacionService.listar());
        model.addAttribute("hospitalizacion", new Hospitalizacion());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("habitaciones", habitacionService.listar());
        model.addAttribute("habitacionId", habitacionId);
        model.addAttribute("editMode", false);
        return "hospitalizaciones";
    }

    @PostMapping
    public String guardar(@RequestParam("paciente.idPaciente") Long idPaciente,
                          @RequestParam("habitacion.idHabitacion") Long idHabitacion,
                          @RequestParam("fechaIngreso") String fechaIngreso,
                          @RequestParam(value = "fechaAlta", required = false) String fechaAlta,
                          @RequestParam("diagnosticoIngreso") String diagnosticoIngreso,
                          @RequestParam("estado") String estado,
                          Model model) {

        try {
            Paciente paciente = pacienteService.buscarPorId(idPaciente);
            Habitacion habitacion = habitacionService.buscarPorId(idHabitacion);
            
            if (paciente == null || habitacion == null) {
                model.addAttribute("error", "Paciente o Habitación no encontrado");
                return listar(null, model);
            }
            
            Hospitalizacion hospitalizacion = new Hospitalizacion();
            hospitalizacion.setPaciente(paciente);
            hospitalizacion.setHabitacion(habitacion);
            hospitalizacion.setFechaIngreso(LocalDate.parse(fechaIngreso));
            
            if (fechaAlta != null && !fechaAlta.isEmpty()) {
                hospitalizacion.setFechaAlta(LocalDate.parse(fechaAlta));
            }
            
            hospitalizacion.setDiagnosticoIngreso(diagnosticoIngreso);
            hospitalizacion.setEstado(Hospitalizacion.EstadoHospitalizacion.valueOf(estado));

            hospitalizacionService.guardar(hospitalizacion);
            return "redirect:/hospitalizaciones";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la hospitalización: " + e.getMessage());
            return listar(null, model);
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Hospitalizacion hospitalizacion = hospitalizacionService.buscarPorId(id);
        model.addAttribute("hospitalizacion", hospitalizacion);
        model.addAttribute("hospitalizaciones", hospitalizacionService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("habitaciones", habitacionService.listar());
        model.addAttribute("editMode", true);
        return "hospitalizaciones";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("paciente.idPaciente") Long idPaciente,
                             @RequestParam("habitacion.idHabitacion") Long idHabitacion,
                             @RequestParam("fechaIngreso") String fechaIngreso,
                             @RequestParam(value = "fechaAlta", required = false) String fechaAlta,
                             @RequestParam("diagnosticoIngreso") String diagnosticoIngreso,
                             @RequestParam("estado") String estado,
                             Model model) {

        try {
            Hospitalizacion hospitalizacion = hospitalizacionService.buscarPorId(id);
            if (hospitalizacion != null) {
                Paciente paciente = pacienteService.buscarPorId(idPaciente);
                Habitacion habitacion = habitacionService.buscarPorId(idHabitacion);
                
                if (paciente == null || habitacion == null) {
                    model.addAttribute("error", "Paciente o Habitación no encontrado");
                    return editar(id, model);
                }
                
                hospitalizacion.setPaciente(paciente);
                hospitalizacion.setHabitacion(habitacion);
                hospitalizacion.setFechaIngreso(LocalDate.parse(fechaIngreso));
                
                if (fechaAlta != null && !fechaAlta.isEmpty()) {
                    hospitalizacion.setFechaAlta(LocalDate.parse(fechaAlta));
                } else {
                    hospitalizacion.setFechaAlta(null);
                }
                
                hospitalizacion.setDiagnosticoIngreso(diagnosticoIngreso);
                hospitalizacion.setEstado(Hospitalizacion.EstadoHospitalizacion.valueOf(estado));

                hospitalizacionService.guardar(hospitalizacion);
            }

            return "redirect:/hospitalizaciones";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la hospitalización: " + e.getMessage());
            return editar(id, model);
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        hospitalizacionService.eliminar(id);
        return "redirect:/hospitalizaciones";
    }
}
