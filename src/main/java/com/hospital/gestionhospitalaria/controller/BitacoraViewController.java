package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Bitacora;
import com.hospital.gestionhospitalaria.model.Usuario;
import com.hospital.gestionhospitalaria.service.BitacoraService;
import com.hospital.gestionhospitalaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bitacora")
public class BitacoraViewController {

    @Autowired
    private BitacoraService bitacoraService;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long usuarioId,
                         @RequestParam(required = false) String accion,
                         Model model) {
        List<Bitacora> bitacoras;
        Usuario usuario = null;
        
        if (usuarioId != null) {
            bitacoras = bitacoraService.buscarPorUsuario(usuarioId);
            usuario = usuarioService.buscarPorId(usuarioId);
        } else if (accion != null && !accion.isEmpty()) {
            bitacoras = bitacoraService.buscarPorAccion(accion);
        } else {
            bitacoras = bitacoraService.listar();
        }
        
        model.addAttribute("bitacoras", bitacoras);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("accion", accion);
        return "bitacora";
    }

    @PostMapping
    public String crearRegistro(@RequestParam("usuario.idUsuario") Long usuarioId,
                               @RequestParam("accion") String accion,
                               @RequestParam("detalle") String detalle,
                               Model model) {
        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId);
            
            if (usuario == null) {
                model.addAttribute("error", "Usuario no encontrado");
                return listar(null, null, model);
            }
            
            Bitacora bitacora = new Bitacora();
            bitacora.setUsuario(usuario);
            bitacora.setAccion(accion);
            bitacora.setDetalle(detalle);
            bitacora.setFechaHora(java.time.LocalDateTime.now());

            bitacoraService.guardar(bitacora);
            return "redirect:/bitacora";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear el registro: " + e.getMessage());
            return listar(null, null, model);
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        bitacoraService.eliminar(id);
        return "redirect:/bitacora";
    }
}
