package com.rollerspeed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@Tag(name = "Alumnos", description = "API para la gestión de alumnos de la escuela de patinaje")
public class AlumnoController {
    private final AlumnoRepository alumnoRepository;

    public AlumnoController(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping
    @Operation(summary = "Listar todos los alumnos", description = "Obtiene una lista de todos los alumnos registrados. Puede filtrar por nombre si se proporciona el parámetro.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alumno.class)))
    })
    public List<Alumno> listar(
            @Parameter(description = "Nombre del alumno para filtrar (opcional)", required = false)
            @RequestParam(required = false) String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return alumnoRepository.findAll();
        }
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre.trim());
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo alumno", description = "Crea un nuevo registro de alumno en el sistema con la información proporcionada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Alumno registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alumno.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Alumno> registrar(
            @Parameter(description = "Datos del alumno a registrar", required = true)
            @Valid @RequestBody @NonNull Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoRepository.save(alumno));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar alumno por ID", description = "Obtiene los detalles de un alumno específico mediante su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alumno encontrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alumno.class))),
        @ApiResponse(responseCode = "404", description = "Alumno no encontrado con el ID proporcionado")
    })
    public Alumno buscarPorId(
            @Parameter(description = "ID del alumno a buscar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de un alumno", description = "Actualiza los datos de un alumno existente. Solo se modifican los campos proporcionados en el request body.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alumno actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alumno.class))),
        @ApiResponse(responseCode = "404", description = "Alumno no encontrado con el ID proporcionado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public Alumno actualizar(
            @Parameter(description = "ID del alumno a actualizar", required = true, example = "1")
            @PathVariable @NonNull Long id,
            @Parameter(description = "Nuevos datos del alumno", required = true)
            @Valid @RequestBody @NonNull Alumno datos) {
        Alumno alumno = buscarPorId(id);
        alumno.setNombre(datos.getNombre());
        alumno.setEmail(datos.getEmail());
        alumno.setTelefono(datos.getTelefono());
        alumno.setEdad(datos.getEdad());
        alumno.setNivel(datos.getNivel());
        return alumnoRepository.save(alumno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno", description = "Elimina permanentemente un alumno del sistema mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Alumno eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Alumno no encontrado con el ID proporcionado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del alumno a eliminar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
        alumnoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
