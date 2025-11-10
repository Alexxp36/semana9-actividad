package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "receta_medica")
public class RecetaMedica {

    @Id
    private String idReceta;

    private String idConsulta;
    private String indicaciones;
}
