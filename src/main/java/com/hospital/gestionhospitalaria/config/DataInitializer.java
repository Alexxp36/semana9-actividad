package com.hospital.gestionhospitalaria.config;

import com.hospital.gestionhospitalaria.model.*;
import com.hospital.gestionhospitalaria.repository.MedicoEspecialidadRepository;
import com.hospital.gestionhospitalaria.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EspecialidadService especialidadService;
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;
    
    @Autowired
    private HabitacionService habitacionService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        // Solo inicializar si no hay datos
        if (especialidadService.listar().isEmpty()) {
            initializeEspecialidades();
        }
        
        if (medicoService.listar().isEmpty()) {
            initializeMedicos();
        }
        
        if (pacienteService.listar().isEmpty()) {
            initializePacientes();
        }
        
        if (habitacionService.listar().isEmpty()) {
            initializeHabitaciones();
        }
        
        if (usuarioService.listar().isEmpty()) {
            initializeUsuarios();
        }
    }

    private void initializeEspecialidades() {
        Especialidad especialidad1 = new Especialidad();
        especialidad1.setNombre("Cardiología");
        especialidad1.setDescripcion("Especialidad médica que se ocupa del diagnóstico y tratamiento de las enfermedades del corazón");
        especialidadService.guardar(especialidad1);

        Especialidad especialidad2 = new Especialidad();
        especialidad2.setNombre("Neurología");
        especialidad2.setDescripcion("Especialidad médica que trata los trastornos del sistema nervioso");
        especialidadService.guardar(especialidad2);

        Especialidad especialidad3 = new Especialidad();
        especialidad3.setNombre("Pediatría");
        especialidad3.setDescripcion("Especialidad médica que estudia al niño y sus enfermedades");
        especialidadService.guardar(especialidad3);
    }

    private void initializeMedicos() {
        // Obtener especialidades
        Especialidad cardiologia = especialidadService.listar().stream()
            .filter(e -> "Cardiología".equals(e.getNombre()))
            .findFirst().orElse(null);
        
        Especialidad neurologia = especialidadService.listar().stream()
            .filter(e -> "Neurología".equals(e.getNombre()))
            .findFirst().orElse(null);

        Medico medico1 = new Medico();
        medico1.setNombres("Juan Carlos");
        medico1.setApellidos("García López");
        medico1.setColegiatura("CM12345");
        medico1.setCorreo("juan.garcia@hospital.com");
        medico1.setTelefono("555-0101");
        medico1.setEstado(Medico.EstadoMedico.ACTIVO);
        medicoService.guardar(medico1);

        Medico medico2 = new Medico();
        medico2.setNombres("María Elena");
        medico2.setApellidos("Rodríguez Martín");
        medico2.setColegiatura("CM67890");
        medico2.setCorreo("maria.rodriguez@hospital.com");
        medico2.setTelefono("555-0102");
        medico2.setEstado(Medico.EstadoMedico.ACTIVO);
        medicoService.guardar(medico2);
        
        // Crear relaciones médico-especialidad
        if (cardiologia != null) {
            MedicoEspecialidad me1 = new MedicoEspecialidad();
            me1.setMedico(medico1);
            me1.setEspecialidad(cardiologia);
            medicoEspecialidadRepository.save(me1);
        }
        
        if (neurologia != null) {
            MedicoEspecialidad me2 = new MedicoEspecialidad();
            me2.setMedico(medico2);
            me2.setEspecialidad(neurologia);
            medicoEspecialidadRepository.save(me2);
        }
    }

    private void initializePacientes() {
        Paciente paciente1 = new Paciente();
        paciente1.setDni("11111111");
        paciente1.setNombres("Ana María");
        paciente1.setApellidos("González Pérez");
        paciente1.setFechaNacimiento("1985-03-15");
        paciente1.setSexo("F");
        paciente1.setDireccion("Calle Principal 123");
        paciente1.setTelefono("555-1001");
        paciente1.setCorreo("ana.gonzalez@email.com");
        paciente1.setEstado(Paciente.EstadoPaciente.ACTIVO);
        pacienteService.guardar(paciente1);

        Paciente paciente2 = new Paciente();
        paciente2.setDni("22222222");
        paciente2.setNombres("Carlos Alberto");
        paciente2.setApellidos("Martín Sánchez");
        paciente2.setFechaNacimiento("1978-07-22");
        paciente2.setSexo("M");
        paciente2.setDireccion("Avenida Central 456");
        paciente2.setTelefono("555-1002");
        paciente2.setCorreo("carlos.martin@email.com");
        paciente2.setEstado(Paciente.EstadoPaciente.ACTIVO);
        pacienteService.guardar(paciente2);
    }
    
    private void initializeHabitaciones() {
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setNumero("101");
        habitacion1.setTipo(Habitacion.TipoHabitacion.GENERAL);
        habitacion1.setEstado(Habitacion.EstadoHabitacion.DISPONIBLE);
        habitacionService.guardar(habitacion1);
        
        Habitacion habitacion2 = new Habitacion();
        habitacion2.setNumero("UCI-01");
        habitacion2.setTipo(Habitacion.TipoHabitacion.UCI);
        habitacion2.setEstado(Habitacion.EstadoHabitacion.DISPONIBLE);
        habitacionService.guardar(habitacion2);
    }
    
    private void initializeUsuarios() {
        Usuario admin = new Usuario();
        admin.setNombreUsuario("admin");
        admin.setContrasena("admin123");
        admin.setRol(Usuario.RolUsuario.ADMIN);
        usuarioService.guardar(admin);
        
        Usuario recepcionista = new Usuario();
        recepcionista.setNombreUsuario("recepcion");
        recepcionista.setContrasena("recepcion123");
        recepcionista.setRol(Usuario.RolUsuario.RECEPCIONISTA);
        usuarioService.guardar(recepcionista);
    }
}
