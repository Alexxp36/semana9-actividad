package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Factura;
import com.hospital.gestionhospitalaria.model.Paciente;
import com.hospital.gestionhospitalaria.service.FacturaService;
import com.hospital.gestionhospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/facturas")
public class FacturaViewController {

    @Autowired
    private FacturaService facturaService;
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaService.listar());
        model.addAttribute("factura", new Factura());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", false);
        return "facturas";
    }

    @PostMapping
    public String guardar(@RequestParam("paciente.idPaciente") Long idPaciente,
                          @RequestParam("fechaEmision") String fechaEmision,
                          @RequestParam("total") Double total,
                          @RequestParam("estado") String estado,
                          Model model) {

        try {
            Paciente paciente = pacienteService.buscarPorId(idPaciente);
            
            if (paciente == null) {
                model.addAttribute("error", "Paciente no encontrado");
                return listar(model);
            }
            
            Factura factura = new Factura();
            factura.setPaciente(paciente);
            factura.setFechaEmision(LocalDate.parse(fechaEmision));
            factura.setTotal(total);
            factura.setEstado(Factura.EstadoFactura.valueOf(estado));

            facturaService.guardar(factura);
            return "redirect:/facturas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la factura: " + e.getMessage());
            return listar(model);
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Factura factura = facturaService.buscarPorId(id);
        model.addAttribute("factura", factura);
        model.addAttribute("facturas", facturaService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("editMode", true);
        return "facturas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("paciente.idPaciente") Long idPaciente,
                             @RequestParam("fechaEmision") String fechaEmision,
                             @RequestParam("total") Double total,
                             @RequestParam("estado") String estado,
                             Model model) {

        try {
            Factura factura = facturaService.buscarPorId(id);
            if (factura != null) {
                Paciente paciente = pacienteService.buscarPorId(idPaciente);
                
                if (paciente == null) {
                    model.addAttribute("error", "Paciente no encontrado");
                    return editar(id, model);
                }
                
                factura.setPaciente(paciente);
                factura.setFechaEmision(LocalDate.parse(fechaEmision));
                factura.setTotal(total);
                factura.setEstado(Factura.EstadoFactura.valueOf(estado));

                facturaService.guardar(factura);
            }

            return "redirect:/facturas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la factura: " + e.getMessage());
            return editar(id, model);
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        facturaService.eliminar(id);
        return "redirect:/facturas";
    }

    // Método para ver detalles de una factura
    @GetMapping("/{id}/detalles")
    public String verDetalles(@PathVariable Long id, Model model) {
        Factura factura = facturaService.buscarPorId(id);
        model.addAttribute("factura", factura);
        return "redirect:/detalle-facturas?facturaId=" + id;
    }

    // Método para calcular total automáticamente
    @PostMapping("/{id}/calcular-total")
    public String calcularTotal(@PathVariable Long id, Model model) {
        try {
            Factura factura = facturaService.buscarPorId(id);
            if (factura != null) {
                // Aquí se podría implementar la lógica para calcular el total
                // basado en los detalles de la factura
                facturaService.guardar(factura);
            }
            return "redirect:/facturas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al calcular el total: " + e.getMessage());
            return "redirect:/facturas";
        }
    }
}
