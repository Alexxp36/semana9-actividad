package com.hospital.gestionhospitalaria.controller;

import com.hospital.gestionhospitalaria.model.Factura;
import com.hospital.gestionhospitalaria.model.DetalleFactura;
import com.hospital.gestionhospitalaria.service.FacturaService;
import com.hospital.gestionhospitalaria.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detalle-facturas")
public class DetalleFacturaViewController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;
    
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public String listar(@RequestParam(required = false) Long facturaId, Model model) {
        List<DetalleFactura> detalles;
        Factura factura = null;
        
        if (facturaId != null) {
            detalles = detalleFacturaService.buscarPorFactura(facturaId);
            factura = facturaService.buscarPorId(facturaId);
        } else {
            detalles = detalleFacturaService.listar();
        }
        
        model.addAttribute("detalles", detalles);
        model.addAttribute("detalle", new DetalleFactura());
        model.addAttribute("facturas", facturaService.listar());
        model.addAttribute("factura", factura);
        model.addAttribute("facturaId", facturaId);
        model.addAttribute("editMode", false);
        return "detalle-facturas";
    }

    @PostMapping
    public String guardar(@RequestParam("factura.idFactura") Long facturaId,
                          @RequestParam("concepto") String concepto,
                          @RequestParam("monto") Double monto,
                          @RequestParam("tipoConcepto") String tipoConcepto,
                          Model model) {

        try {
            Factura factura = facturaService.buscarPorId(facturaId);
            
            if (factura == null) {
                model.addAttribute("error", "Factura no encontrada");
                return "redirect:/detalle-facturas?facturaId=" + facturaId;
            }
            
            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura);
            detalle.setConcepto(concepto);
            detalle.setMonto(monto);
            detalle.setTipoConcepto(DetalleFactura.TipoConcepto.valueOf(tipoConcepto));

            detalleFacturaService.guardar(detalle);
            return "redirect:/detalle-facturas?facturaId=" + facturaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el detalle: " + e.getMessage());
            return "redirect:/detalle-facturas?facturaId=" + facturaId;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id,
                        @RequestParam(required = false) Long facturaId,
                        Model model) {
        DetalleFactura detalle = detalleFacturaService.buscarPorId(id);
        Factura factura = null;
        
        if (facturaId != null) {
            factura = facturaService.buscarPorId(facturaId);
        }
        
        model.addAttribute("detalle", detalle);
        model.addAttribute("detalles", detalleFacturaService.buscarPorFactura(detalle.getFactura().getIdFactura()));
        model.addAttribute("facturas", facturaService.listar());
        model.addAttribute("factura", factura);
        model.addAttribute("facturaId", facturaId);
        model.addAttribute("editMode", true);
        return "detalle-facturas";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("factura.idFactura") Long facturaId,
                             @RequestParam("concepto") String concepto,
                             @RequestParam("monto") Double monto,
                             @RequestParam("tipoConcepto") String tipoConcepto,
                             Model model) {

        try {
            DetalleFactura detalle = detalleFacturaService.buscarPorId(id);
            if (detalle != null) {
                detalle.setConcepto(concepto);
                detalle.setMonto(monto);
                detalle.setTipoConcepto(DetalleFactura.TipoConcepto.valueOf(tipoConcepto));

                detalleFacturaService.guardar(detalle);
            }

            return "redirect:/detalle-facturas?facturaId=" + facturaId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar el detalle: " + e.getMessage());
            return "redirect:/detalle-facturas?facturaId=" + facturaId;
        }
    }

    @GetMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long facturaId) {
        DetalleFactura detalle = detalleFacturaService.buscarPorId(id);
        Long facturaIdRedirect = facturaId != null ? facturaId : detalle.getFactura().getIdFactura();
        detalleFacturaService.eliminar(id);
        return "redirect:/detalle-facturas?facturaId=" + facturaIdRedirect;
    }
}
