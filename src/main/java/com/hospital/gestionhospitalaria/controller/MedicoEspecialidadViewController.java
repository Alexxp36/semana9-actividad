package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Medico;
import com.hospital.gestionhospitalaria.model.Especialidad;
import com.hospital.gestionhospitalaria.model.MedicoEspecialidad;
import com.hospital.gestionhospitalaria.service.MedicoService;
import com.hospital.gestionhospitalaria.service.EspecialidadService;
import com.hospital.gestionhospitalaria.service.MedicoEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medico-especialidades")
public class MedicoEspecialidadViewController {

    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long medicoId, Model model) {
        List<MedicoEspecialidad> asignaciones;
        Medico medico = null;
        
        if (medicoId != null) {
            asignaciones = medicoEspecialidadService.buscarPorMedico(medicoId);
            medico = medicoService.buscarPorId(medicoId);
        } else {
            asignaciones = medicoEspecialidadService.listar();
        }
        
        model.addAttribute("asignaciones", asignaciones);
        model.addAttribute("asignacion", new MedicoEspecialidad());
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("especialidades", especialidadService.listar());
        model.addAttribute("medico", medico);
        model.addAttribute("medicoId", medicoId);
        model.addAttribute("editMode", false);
        return "medico-especialidades";
    }

    @PostMapping
    public String guardar(@RequestParam("medico.idMedico") Long medicoId,
                          @RequestParam("especialidad.idEspecialidad") Long especialidadId,
                          Model model) {

        try {
            Medico medico = medicoService.buscarPorId(medicoId);
            Especialidad especialidad = especialidadService.buscarPorId(especialidadId);
            
            if (medico == null || especialidad == null) {
                model.addAttribute("error", "Médico o Especialidad no encontrado");
                return "redirect:/medico-especialidades?medicoId=" + medicoId;
            }
            
            // Verificar si ya existe la asignación
            if (medicoEspecialidadService.existeAsignacion(medicoId, especialidadId)) {
                model.addAttribute("error", "Esta asignación ya existe");
                return "redirect:/medico-especialidades?medicoId=" + medicoId;
            }
            
            MedicoEspecialidad asignacion = new MedicoEspecialidad();
            asignacion.setMedico(medico);
            asignacion.setEspecialidad(especialidad);

            medicoEspecialidadService.guardar(asignacion);
            return "redirect:/medico-especialidades?medicoId=" + medicoId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la asignación: " + e.getMessage());
            return "redirect:/medico-especialidades?medicoId=" + medicoId;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id,
                        @RequestParam(required = false) Long medicoId,
                        Model model) {
        MedicoEspecialidad asignacion = medicoEspecialidadService.buscarPorId(id);
        Medico medico = null;
        
        if (medicoId != null) {
            medico = medicoService.buscarPorId(medicoId);
        }
        
        model.addAttribute("asignacion", asignacion);
        model.addAttribute("asignaciones", medicoEspecialidadService.buscarPorMedico(asignacion.getMedico().getIdMedico()));
        model.addAttribute("medicos", medicoService.listar());
        model.addAttribute("especialidades", especialidadService.listar());
        model.addAttribute("medico", medico);
        model.addAttribute("medicoId", medicoId);
        model.addAttribute("editMode", true);
        return "medico-especialidades";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("medico.idMedico") Long medicoId,
                             @RequestParam("especialidad.idEspecialidad") Long especialidadId,
                             Model model) {

        try {
            MedicoEspecialidad asignacion = medicoEspecialidadService.buscarPorId(id);
            if (asignacion != null) {
                Medico medico = medicoService.buscarPorId(medicoId);
                Especialidad especialidad = especialidadService.buscarPorId(especialidadId);
                
                if (medico == null || especialidad == null) {
                    model.addAttribute("error", "Médico o Especialidad no encontrado");
                    return "redirect:/medico-especialidades?medicoId=" + medicoId;
                }
                
                asignacion.setMedico(medico);
                asignacion.setEspecialidad(especialidad);

                medicoEspecialidadService.guardar(asignacion);
            }

            return "redirect:/medico-especialidades?medicoId=" + medicoId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la asignación: " + e.getMessage());
            return "redirect:/medico-especialidades?medicoId=" + medicoId;
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long medicoId) {
        MedicoEspecialidad asignacion = medicoEspecialidadService.buscarPorId(id);
        Long medicoIdRedirect = medicoId != null ? medicoId : asignacion.getMedico().getIdMedico();
        medicoEspecialidadService.eliminar(id);
        return "redirect:/medico-especialidades?medicoId=" + medicoIdRedirect;
    }
}
