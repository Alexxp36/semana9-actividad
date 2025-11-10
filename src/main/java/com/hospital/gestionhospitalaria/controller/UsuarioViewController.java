package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Usuario;
import com.hospital.gestionhospitalaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("editMode", false);
        return "usuarios";
    }

    @PostMapping
    public String guardar(@RequestParam("nombreUsuario") String nombreUsuario,
                          @RequestParam("contrasena") String contrasena,
                          @RequestParam("rol") String rol,
                          Model model) {

        try {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setRol(Usuario.RolUsuario.valueOf(rol));

            usuarioService.guardar(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el usuario: " + e.getMessage());
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("editMode", false);
            return "usuarios";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("editMode", true);
        return "usuarios";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("nombreUsuario") String nombreUsuario,
                             @RequestParam("contrasena") String contrasena,
                             @RequestParam("rol") String rol,
                             Model model) {

        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            if (usuario != null) {
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);
                usuario.setRol(Usuario.RolUsuario.valueOf(rol));

                usuarioService.guardar(usuario);
            }

            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar el usuario: " + e.getMessage());
            Usuario usuario = usuarioService.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("editMode", true);
            return "usuarios";
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    // Método para cambiar contraseña
    @PostMapping("/{id}/cambiar-contrasena")
    public String cambiarContrasena(@PathVariable Long id,
                                   @RequestParam("nuevaContrasena") String nuevaContrasena,
                                   Model model) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            if (usuario != null) {
                usuario.setContrasena(nuevaContrasena);
                usuarioService.guardar(usuario);
            }
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cambiar la contraseña: " + e.getMessage());
            return "redirect:/usuarios";
        }
    }

    // Método para buscar usuarios por rol
    @GetMapping("/por-rol")
    public String buscarPorRol(@RequestParam("rol") String rol, Model model) {
        try {
            Usuario.RolUsuario rolUsuario = Usuario.RolUsuario.valueOf(rol);
            model.addAttribute("usuarios", usuarioService.buscarPorRol(rolUsuario));
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("editMode", false);
            model.addAttribute("rolFiltro", rol);
            return "usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar usuarios: " + e.getMessage());
            return listar(model);
        }
    }
}
