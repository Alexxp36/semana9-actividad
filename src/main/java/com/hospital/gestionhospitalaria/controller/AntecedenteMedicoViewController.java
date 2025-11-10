package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.AntecedenteMedico;
import com.hospital.gestionhospitalaria.model.HistoriaClinica;
import com.hospital.gestionhospitalaria.service.AntecedenteMedicoService;
import com.hospital.gestionhospitalaria.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/antecedentes")
public class AntecedenteMedicoViewController {

    @Autowired
    private AntecedenteMedicoService antecedenteService;
    
    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long historiaId, Model model) {
        List<AntecedenteMedico> antecedentes;
        HistoriaClinica historiaClinica = null;
        
        if (historiaId != null) {
            antecedentes = antecedenteService.buscarPorHistoria(historiaId);
            historiaClinica = historiaClinicaService.buscarPorId(historiaId);
        } else {
            antecedentes = antecedenteService.listar();
        }
        
        model.addAttribute("antecedentes", antecedentes);
        model.addAttribute("antecedente", new AntecedenteMedico());
        model.addAttribute("historiaClinica", historiaClinica);
        model.addAttribute("historiaId", historiaId);
        model.addAttribute("editMode", false);
        return "antecedentes";
    }

    @PostMapping
    public String guardar(@RequestParam(value = "historiaClinica.idHistoria", required = false) Long historiaId,
                          @RequestParam("tipo") String tipo,
                          @RequestParam("descripcion") String descripcion,
                          Model model) {

        try {
            if (historiaId == null) {
                model.addAttribute("error", "ID de historia clínica requerido");
                return "redirect:/antecedentes";
            }
            
            HistoriaClinica historiaClinica = historiaClinicaService.buscarPorId(historiaId);
            
            if (historiaClinica == null) {
                model.addAttribute("error", "Historia clínica no encontrada");
                return "redirect:/antecedentes?historiaId=" + historiaId;
            }
            
            AntecedenteMedico antecedente = new AntecedenteMedico();
            antecedente.setHistoriaClinica(historiaClinica);
            antecedente.setTipo(AntecedenteMedico.TipoAntecedente.valueOf(tipo));
            antecedente.setDescripcion(descripcion);

            antecedenteService.guardar(antecedente);
            return "redirect:/antecedentes?historiaId=" + historiaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el antecedente: " + e.getMessage());
            return "redirect:/antecedentes?historiaId=" + historiaId;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, 
                        @RequestParam(required = false) Long historiaId,
                        Model model) {
        AntecedenteMedico antecedente = antecedenteService.buscarPorId(id);
        HistoriaClinica historiaClinica = null;
        
        if (historiaId != null) {
            historiaClinica = historiaClinicaService.buscarPorId(historiaId);
        }
        
        model.addAttribute("antecedente", antecedente);
        model.addAttribute("antecedentes", antecedenteService.buscarPorHistoria(antecedente.getHistoriaClinica().getIdHistoria()));
        model.addAttribute("historiaClinica", historiaClinica);
        model.addAttribute("historiaId", historiaId);
        model.addAttribute("editMode", true);
        return "antecedentes";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam(value = "historiaClinica.idHistoria", required = false) Long historiaId,
                             @RequestParam("tipo") String tipo,
                             @RequestParam("descripcion") String descripcion,
                             Model model) {

        try {
            AntecedenteMedico antecedente = antecedenteService.buscarPorId(id);
            if (antecedente != null) {
                antecedente.setTipo(AntecedenteMedico.TipoAntecedente.valueOf(tipo));
                antecedente.setDescripcion(descripcion);

                antecedenteService.guardar(antecedente);
                
                // Si no tenemos historiaId, usar el del antecedente existente
                if (historiaId == null) {
                    historiaId = antecedente.getHistoriaClinica().getIdHistoria();
                }
            }

            return "redirect:/antecedentes?historiaId=" + historiaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar el antecedente: " + e.getMessage());
            return "redirect:/antecedentes?historiaId=" + historiaId;
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long historiaId) {
        AntecedenteMedico antecedente = antecedenteService.buscarPorId(id);
        Long historiaIdRedirect = historiaId != null ? historiaId : antecedente.getHistoriaClinica().getIdHistoria();
        antecedenteService.eliminar(id);
        return "redirect:/antecedentes?historiaId=" + historiaIdRedirect;
    }
}
