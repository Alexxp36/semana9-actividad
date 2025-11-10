package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "especialidad")
public class Especialidad {
    @Id
    private String idEspecialidad;

    private String nombre;
    private String descripcion;
}