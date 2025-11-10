package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Consulta;
import com.hospital.gestionhospitalaria.model.Diagnostico;
import com.hospital.gestionhospitalaria.service.ConsultaService;
import com.hospital.gestionhospitalaria.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/diagnosticos")
public class DiagnosticoViewController {

    @Autowired
    private DiagnosticoService diagnosticoService;
    
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long consultaId, Model model) {
        List<Diagnostico> diagnosticos;
        Consulta consulta = null;
        
        if (consultaId != null) {
            diagnosticos = diagnosticoService.buscarPorConsulta(consultaId);
            consulta = consultaService.buscarPorId(consultaId);
        } else {
            diagnosticos = diagnosticoService.listar();
        }
        
        model.addAttribute("diagnosticos", diagnosticos);
        model.addAttribute("diagnostico", new Diagnostico());
        model.addAttribute("consulta", consulta);
        model.addAttribute("consultaId", consultaId);
        model.addAttribute("editMode", false);
        return "diagnosticos";
    }

    @PostMapping
    public String guardar(@RequestParam("consulta.idConsulta") Long consultaId,
                          @RequestParam("tipo") String tipo,
                          @RequestParam("descripcion") String descripcion,
                          Model model) {

        try {
            Consulta consulta = consultaService.buscarPorId(consultaId);
            
            if (consulta == null) {
                model.addAttribute("error", "Consulta no encontrada");
                return "redirect:/diagnosticos?consultaId=" + consultaId;
            }
            
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setConsulta(consulta);
            diagnostico.setTipo(Diagnostico.TipoDiagnostico.valueOf(tipo));
            diagnostico.setDescripcion(descripcion);

            diagnosticoService.guardar(diagnostico);
            return "redirect:/diagnosticos?consultaId=" + consultaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el diagnóstico: " + e.getMessage());
            return "redirect:/diagnosticos?consultaId=" + consultaId;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, 
                        @RequestParam(required = false) Long consultaId,
                        Model model) {
        Diagnostico diagnostico = diagnosticoService.buscarPorId(id);
        Consulta consulta = null;
        
        if (consultaId != null) {
            consulta = consultaService.buscarPorId(consultaId);
        }
        
        model.addAttribute("diagnostico", diagnostico);
        model.addAttribute("diagnosticos", diagnosticoService.buscarPorConsulta(diagnostico.getConsulta().getIdConsulta()));
        model.addAttribute("consulta", consulta);
        model.addAttribute("consultaId", consultaId);
        model.addAttribute("editMode", true);
        return "diagnosticos";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("consulta.idConsulta") Long consultaId,
                             @RequestParam("tipo") String tipo,
                             @RequestParam("descripcion") String descripcion,
                             Model model) {

        try {
            Diagnostico diagnostico = diagnosticoService.buscarPorId(id);
            if (diagnostico != null) {
                diagnostico.setTipo(Diagnostico.TipoDiagnostico.valueOf(tipo));
                diagnostico.setDescripcion(descripcion);

                diagnosticoService.guardar(diagnostico);
            }

            return "redirect:/diagnosticos?consultaId=" + consultaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar el diagnóstico: " + e.getMessage());
            return "redirect:/diagnosticos?consultaId=" + consultaId;
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long consultaId) {
        Diagnostico diagnostico = diagnosticoService.buscarPorId(id);
        Long consultaIdRedirect = consultaId != null ? consultaId : diagnostico.getConsulta().getIdConsulta();
        diagnosticoService.eliminar(id);
        return "redirect:/diagnosticos?consultaId=" + consultaIdRedirect;
    }
}
