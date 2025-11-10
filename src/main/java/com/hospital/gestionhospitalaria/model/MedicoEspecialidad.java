package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "medico_especialidad")
public class MedicoEspecialidad {

    @Id
    private String idMedicoEsp;

    private String idMedico;
    private String idEspecialidad;
}
