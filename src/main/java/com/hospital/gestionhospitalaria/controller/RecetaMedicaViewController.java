package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Consulta;
import com.hospital.gestionhospitalaria.model.RecetaMedica;
import com.hospital.gestionhospitalaria.model.DetalleReceta;
import com.hospital.gestionhospitalaria.service.ConsultaService;
import com.hospital.gestionhospitalaria.service.RecetaMedicaService;
import com.hospital.gestionhospitalaria.service.DetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetaMedicaViewController {

    @Autowired
    private RecetaMedicaService recetaMedicaService;
    
    @Autowired
    private ConsultaService consultaService;
    
    @Autowired
    private DetalleRecetaService detalleRecetaService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long consultaId, Model model) {
        List<RecetaMedica> recetas;
        Consulta consulta = null;
        
        if (consultaId != null) {
            recetas = recetaMedicaService.buscarPorConsulta(consultaId);
            consulta = consultaService.buscarPorId(consultaId);
        } else {
            recetas = recetaMedicaService.listar();
        }
        
        model.addAttribute("recetas", recetas);
        model.addAttribute("receta", new RecetaMedica());
        model.addAttribute("consulta", consulta);
        model.addAttribute("consultaId", consultaId);
        model.addAttribute("editMode", false);
        return "recetas";
    }

    @PostMapping
    public String guardar(@RequestParam("consulta.idConsulta") Long consultaId,
                          @RequestParam("indicaciones") String indicaciones,
                          Model model) {

        try {
            Consulta consulta = consultaService.buscarPorId(consultaId);
            
            if (consulta == null) {
                model.addAttribute("error", "Consulta no encontrada");
                return "redirect:/recetas?consultaId=" + consultaId;
            }
            
            RecetaMedica receta = new RecetaMedica();
            receta.setConsulta(consulta);
            receta.setIndicaciones(indicaciones);

            recetaMedicaService.guardar(receta);
            return "redirect:/recetas?consultaId=" + consultaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la receta: " + e.getMessage());
            return "redirect:/recetas?consultaId=" + consultaId;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id,
                        @RequestParam(required = false) Long consultaId,
                        Model model) {
        RecetaMedica receta = recetaMedicaService.buscarPorId(id);
        Consulta consulta = null;
        
        if (consultaId != null) {
            consulta = consultaService.buscarPorId(consultaId);
        }
        
        model.addAttribute("receta", receta);
        model.addAttribute("recetas", recetaMedicaService.buscarPorConsulta(receta.getConsulta().getIdConsulta()));
        model.addAttribute("consulta", consulta);
        model.addAttribute("consultaId", consultaId);
        model.addAttribute("editMode", true);
        return "recetas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("consulta.idConsulta") Long consultaId,
                             @RequestParam("indicaciones") String indicaciones,
                             Model model) {

        try {
            RecetaMedica receta = recetaMedicaService.buscarPorId(id);
            if (receta != null) {
                receta.setIndicaciones(indicaciones);
                recetaMedicaService.guardar(receta);
            }

            return "redirect:/recetas?consultaId=" + consultaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la receta: " + e.getMessage());
            return "redirect:/recetas?consultaId=" + consultaId;
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long consultaId) {
        RecetaMedica receta = recetaMedicaService.buscarPorId(id);
        Long consultaIdRedirect = consultaId != null ? consultaId : receta.getConsulta().getIdConsulta();
        recetaMedicaService.eliminar(id);
        return "redirect:/recetas?consultaId=" + consultaIdRedirect;
    }

    // Métodos para gestionar detalles de receta
    @GetMapping("/{id}/detalles")
    public String verDetalles(@PathVariable Long id, Model model) {
        RecetaMedica receta = recetaMedicaService.buscarPorId(id);
        List<DetalleReceta> detalles = detalleRecetaService.buscarPorReceta(id);
        
        model.addAttribute("receta", receta);
        model.addAttribute("detalles", detalles);
        model.addAttribute("detalle", new DetalleReceta());
        return "detalle-recetas";
    }

    @PostMapping("/{id}/detalles")
    public String agregarDetalle(@PathVariable Long id,
                                @RequestParam("medicamento") String medicamento,
                                @RequestParam("dosis") String dosis,
                                @RequestParam("frecuencia") String frecuencia,
                                @RequestParam("duracion") String duracion,
                                Model model) {
        try {
            RecetaMedica receta = recetaMedicaService.buscarPorId(id);
            if (receta != null) {
                DetalleReceta detalle = new DetalleReceta();
                detalle.setRecetaMedica(receta);
                detalle.setMedicamento(medicamento);
                detalle.setDosis(dosis);
                detalle.setFrecuencia(frecuencia);
                detalle.setDuracion(duracion);
                
                detalleRecetaService.guardar(detalle);
            }
            return "redirect:/recetas/" + id + "/detalles";
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar detalle: " + e.getMessage());
            return "redirect:/recetas/" + id + "/detalles";
        }
    }

    @GetMapping("/detalles/{detalleId}/delete")
    public String eliminarDetalle(@PathVariable Long detalleId, @RequestParam("recetaId") Long recetaId) {
        detalleRecetaService.eliminar(detalleId);
        return "redirect:/recetas/" + recetaId + "/detalles";
    }
}
