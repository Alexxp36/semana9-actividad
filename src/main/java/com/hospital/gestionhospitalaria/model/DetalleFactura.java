package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "detalle_factura")
public class DetalleFactura {

    @Id
    private String idDetalleFactura;

    private String idFactura;
    private String concepto;
    private Double monto;
    private String tipoConcepto;

    public enum TipoConcepto {
        CONSULTA("Consulta"),
        MEDICAMENTO("Medicamento"),
        PROCEDIMIENTO("Procedimiento"),
        HOSPITALIZACION("Hospitalización"),
        LABORATORIO("Laboratorio");

        private final String descripcion;

        TipoConcepto(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
