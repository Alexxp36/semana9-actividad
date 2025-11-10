package com.hospital.gestionhospitalaria.repository;

import com.hospital.gestionhospitalaria.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    List<Usuario> findByRol(String rol);
}
