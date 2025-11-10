package com.hospital.gestionhospitalaria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "bitacora")
public class Bitacora {

    @Id
    private String idBitacora;

    private String idUsuario;
    private String accion;

    @Field("fechaHora")
    private LocalDateTime fechaHora;

    private String detalle;
}
