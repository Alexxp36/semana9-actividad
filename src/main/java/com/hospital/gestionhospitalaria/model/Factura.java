package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDate;

@Data
@Document(collection = "factura")
public class Factura {

    @Id
    private String idFactura;

    private String idPaciente;

    @Field("fechaEmision")
    private LocalDate fechaEmision;

    private Double total;
    private String estado;

    public enum EstadoFactura {
        PENDIENTE("Pendiente"),
        PAGADO("Pagado");

        private final String descripcion;

        EstadoFactura(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
