package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Habitacion;
import com.hospital.gestionhospitalaria.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionViewController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("habitaciones", habitacionService.listar());
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("editMode", false);
        return "habitaciones";
    }

    @PostMapping
    public String guardar(@RequestParam("numero") String numero,
                          @RequestParam("tipo") String tipo,
                          @RequestParam("estado") String estado,
                          Model model) {

        try {
            Habitacion habitacion = new Habitacion();
            habitacion.setNumero(numero);
            habitacion.setTipo(Habitacion.TipoHabitacion.valueOf(tipo));
            habitacion.setEstado(Habitacion.EstadoHabitacion.valueOf(estado));

            habitacionService.guardar(habitacion);
            return "redirect:/habitaciones";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la habitación: " + e.getMessage());
            model.addAttribute("habitaciones", habitacionService.listar());
            model.addAttribute("habitacion", new Habitacion());
            model.addAttribute("editMode", false);
            return "habitaciones";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Habitacion habitacion = habitacionService.buscarPorId(id);
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("habitaciones", habitacionService.listar());
        model.addAttribute("editMode", true);
        return "habitaciones";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("numero") String numero,
                             @RequestParam("tipo") String tipo,
                             @RequestParam("estado") String estado,
                             Model model) {

        try {
            Habitacion habitacion = habitacionService.buscarPorId(id);
            if (habitacion != null) {
                habitacion.setNumero(numero);
                habitacion.setTipo(Habitacion.TipoHabitacion.valueOf(tipo));
                habitacion.setEstado(Habitacion.EstadoHabitacion.valueOf(estado));

                habitacionService.guardar(habitacion);
            }

            return "redirect:/habitaciones";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar la habitación: " + e.getMessage());
            Habitacion habitacion = habitacionService.buscarPorId(id);
            model.addAttribute("habitacion", habitacion);
            model.addAttribute("habitaciones", habitacionService.listar());
            model.addAttribute("editMode", true);
            return "habitaciones";
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return "redirect:/habitaciones";
    }
}
