-- Script completo para la base de datos del Sistema de Gestión Hospitalaria
-- Eliminar la base de datos existente si existe
DROP DATABASE IF EXISTS hospitaldb;

-- Crear la nueva base de datos con configuración UTF-8
CREATE DATABASE hospitaldb 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la nueva base de datos
USE hospitaldb;

-- 1. Módulo de Pacientes
-- Crear tabla especialidades
CREATE TABLE especialidad (
    id_especialidad BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500)
);

-- Crear tabla médicos
CREATE TABLE medico (
    id_medico BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    colegiatura VARCHAR(50) UNIQUE,
    telefono VARCHAR(20),
    correo VARCHAR(255),
    estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
);

-- Relación muchos a muchos entre médicos y especialidades
CREATE TABLE medico_especialidad (
    id_medico_esp BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_medico BIGINT,
    id_especialidad BIGINT,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE,
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id_especialidad) ON DELETE CASCADE,
    UNIQUE KEY unique_medico_especialidad (id_medico, id_especialidad)
);

-- Crear tabla pacientes
CREATE TABLE paciente (
    id_paciente BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    sexo ENUM('M', 'F'),
    direccion VARCHAR(500),
    telefono VARCHAR(20),
    correo VARCHAR(255),
    estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
);

-- Crear tabla historia clínica
CREATE TABLE historia_clinica (
    id_historia BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT,
    fecha_apertura DATE NOT NULL,
    observaciones TEXT,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE
);

-- Crear tabla antecedentes médicos
CREATE TABLE antecedente_medico (
    id_antecedente BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_historia BIGINT,
    tipo ENUM('ALERGIAS', 'ENFERMEDADES_PREVIAS', 'CIRUGIAS', 'MEDICAMENTOS') NOT NULL,
    descripcion TEXT NOT NULL,
    FOREIGN KEY (id_historia) REFERENCES historia_clinica(id_historia) ON DELETE CASCADE
);

-- 2. Módulo de Citas Médicas
CREATE TABLE cita (
    id_cita BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT,
    id_medico BIGINT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo VARCHAR(500),
    estado ENUM('PROGRAMADA', 'ATENDIDA', 'CANCELADA') DEFAULT 'PROGRAMADA',
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE
);

-- 3. Módulo de Consultas y Diagnósticos
CREATE TABLE consulta (
    id_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cita BIGINT,
    id_medico BIGINT,
    id_paciente BIGINT,
    id_historia BIGINT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo_consulta VARCHAR(500),
    observaciones TEXT,
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita) ON DELETE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE,
    FOREIGN KEY (id_historia) REFERENCES historia_clinica(id_historia) ON DELETE CASCADE
);

-- Crear tabla diagnósticos
CREATE TABLE diagnostico (
    id_diagnostico BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta BIGINT,
    descripcion TEXT NOT NULL,
    tipo ENUM('PRESUNTIVO', 'DEFINITIVO') NOT NULL,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id_consulta) ON DELETE CASCADE
);

-- Crear tabla recetas médicas
CREATE TABLE receta_medica (
    id_receta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta BIGINT,
    indicaciones TEXT,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id_consulta) ON DELETE CASCADE
);

-- Crear tabla detalles de receta
CREATE TABLE detalle_receta (
    id_detalle_receta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_receta BIGINT,
    medicamento VARCHAR(255) NOT NULL,
    dosis VARCHAR(100),
    frecuencia VARCHAR(100),
    duracion VARCHAR(100),
    FOREIGN KEY (id_receta) REFERENCES receta_medica(id_receta) ON DELETE CASCADE
);

-- 4. Módulo de Hospitalización
CREATE TABLE habitacion (
    id_habitacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(10) UNIQUE NOT NULL,
    tipo ENUM('UCI', 'GENERAL', 'EMERGENCIA') NOT NULL,
    estado ENUM('DISPONIBLE', 'OCUPADA', 'MANTENIMIENTO') DEFAULT 'DISPONIBLE'
);

CREATE TABLE hospitalizacion (
    id_hosp BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT,
    id_habitacion BIGINT,
    fecha_ingreso DATE NOT NULL,
    fecha_alta DATE,
    diagnostico_ingreso TEXT,
    estado ENUM('ACTIVO', 'DADO_DE_ALTA') DEFAULT 'ACTIVO',
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE,
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion) ON DELETE CASCADE
);

-- 5. Módulo de Facturación
CREATE TABLE factura (
    id_factura BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT,
    fecha_emision DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    estado ENUM('PENDIENTE', 'PAGADO') DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE
);

CREATE TABLE detalle_factura (
    id_detalle_factura BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_factura BIGINT,
    concepto VARCHAR(255) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    tipo_concepto ENUM('CONSULTA', 'MEDICAMENTO', 'PROCEDIMIENTO', 'HOSPITALIZACION', 'LABORATORIO') NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES factura(id_factura) ON DELETE CASCADE
);

-- 6. Módulo de Administración y Seguridad
CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'MEDICO', 'RECEPCIONISTA', 'ENFERMERA') NOT NULL
);

CREATE TABLE bitacora (
    id_bitacora BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT,
    accion VARCHAR(255) NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    detalle TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Insertar datos de prueba
-- Especialidades
INSERT INTO especialidad (nombre, descripcion) VALUES
('Cardiología', 'Especialidad médica que se ocupa del diagnóstico y tratamiento de las enfermedades del corazón'),
('Neurología', 'Especialidad médica que trata los trastornos del sistema nervioso'),
('Pediatría', 'Especialidad médica que estudia al niño y sus enfermedades'),
('Traumatología', 'Especialidad médica que se encarga del diagnóstico y tratamiento de las lesiones del sistema musculoesquelético'),
('Ginecología', 'Especialidad médica que se ocupa de la salud reproductiva de la mujer'),
('Medicina Interna', 'Especialidad médica que se encarga del diagnóstico y tratamiento de enfermedades del adulto'),
('Cirugía General', 'Especialidad médica que se encarga de procedimientos quirúrgicos');

-- Médicos
INSERT INTO medico (nombres, apellidos, colegiatura, telefono, correo, estado) VALUES
('Juan Carlos', 'García López', 'CM12345', '555-0101', 'juan.garcia@hospital.com', 'ACTIVO'),
('María Elena', 'Rodríguez Martín', 'CM67890', '555-0102', 'maria.rodriguez@hospital.com', 'ACTIVO'),
('Pedro Antonio', 'Sánchez Ruiz', 'CM54321', '555-0103', 'pedro.sanchez@hospital.com', 'ACTIVO'),
('Ana Sofía', 'Martín García', 'CM98765', '555-0104', 'ana.martin@hospital.com', 'ACTIVO'),
('Carlos Miguel', 'López Pérez', 'CM11111', '555-0105', 'carlos.lopez@hospital.com', 'ACTIVO');

-- Relaciones médico-especialidad
INSERT INTO medico_especialidad (id_medico, id_especialidad) VALUES
(1, 1), -- Juan Carlos - Cardiología
(2, 2), -- María Elena - Neurología
(3, 3), -- Pedro Antonio - Pediatría
(4, 4), -- Ana Sofía - Traumatología
(5, 5), -- Carlos Miguel - Ginecología
(1, 6), -- Juan Carlos - Medicina Interna
(2, 6); -- María Elena - Medicina Interna

-- Pacientes
INSERT INTO paciente (dni, nombres, apellidos, fecha_nacimiento, sexo, direccion, telefono, correo, estado) VALUES
('11111111', 'Ana María', 'González Pérez', '1985-03-15', 'F', 'Calle Principal 123', '555-1001', 'ana.gonzalez@email.com', 'ACTIVO'),
('22222222', 'Carlos Alberto', 'Martín Sánchez', '1978-07-22', 'M', 'Avenida Central 456', '555-1002', 'carlos.martin@email.com', 'ACTIVO'),
('33333333', 'María José', 'López García', '1990-12-08', 'F', 'Plaza Mayor 789', '555-1003', 'maria.lopez@email.com', 'ACTIVO'),
('44444444', 'José Luis', 'Pérez Rodríguez', '1982-05-30', 'M', 'Calle Secundaria 321', '555-1004', 'jose.perez@email.com', 'ACTIVO'),
('55555555', 'Laura Beatriz', 'Sánchez López', '1995-09-14', 'F', 'Avenida Norte 654', '555-1005', 'laura.sanchez@email.com', 'ACTIVO');

-- Habitaciones
INSERT INTO habitacion (numero, tipo, estado) VALUES
('101', 'GENERAL', 'DISPONIBLE'),
('102', 'GENERAL', 'DISPONIBLE'),
('103', 'GENERAL', 'DISPONIBLE'),
('201', 'GENERAL', 'DISPONIBLE'),
('202', 'GENERAL', 'DISPONIBLE'),
('UCI-01', 'UCI', 'DISPONIBLE'),
('UCI-02', 'UCI', 'DISPONIBLE'),
('EMG-01', 'EMERGENCIA', 'DISPONIBLE'),
('EMG-02', 'EMERGENCIA', 'DISPONIBLE');

-- Usuarios del sistema
INSERT INTO usuario (nombre_usuario, contrasena, rol) VALUES
('admin', 'admin123', 'ADMIN'),
('recepcion', 'recepcion123', 'RECEPCIONISTA'),
('medico1', 'medico123', 'MEDICO'),
('enfermera1', 'enfermera123', 'ENFERMERA');

-- Verificar que las tablas se crearon correctamente
SHOW TABLES;

-- Verificar los datos insertados
SELECT 'Especialidades' as Tabla, COUNT(*) as Registros FROM especialidad
UNION ALL
SELECT 'Médicos' as Tabla, COUNT(*) as Registros FROM medico
UNION ALL
SELECT 'Relaciones Médico-Especialidad' as Tabla, COUNT(*) as Registros FROM medico_especialidad
UNION ALL
SELECT 'Pacientes' as Tabla, COUNT(*) as Registros FROM paciente
UNION ALL
SELECT 'Habitaciones' as Tabla, COUNT(*) as Registros FROM habitacion
UNION ALL
SELECT 'Usuarios' as Tabla, COUNT(*) as Registros FROM usuario;
