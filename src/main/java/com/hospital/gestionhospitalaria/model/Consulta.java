package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document(collection = "consulta")
public class Consulta {

    @Id
    private String idConsulta;

    private String idCita;
    private String idMedico;
    private String idPaciente;
    private String idHistoria;

    private LocalDate fecha;
    private LocalTime hora;

    @Field("motivoConsulta")
    private String motivoConsulta;

    private String observaciones;
}
