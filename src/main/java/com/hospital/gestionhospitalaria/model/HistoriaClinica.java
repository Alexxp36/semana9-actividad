package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDate;

@Data
@Document(collection = "historia_clinica")
public class HistoriaClinica {

    @Id
    private String idHistoria;

    private String idPaciente;

    @Field("fechaApertura")
    private LocalDate fechaApertura;

    private String observaciones;
}
