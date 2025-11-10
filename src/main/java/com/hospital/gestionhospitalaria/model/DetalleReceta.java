package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "detalle_receta")
public class DetalleReceta {

    @Id
    private String idDetalleReceta;

    private String idReceta;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private String duracion;
}
