package com.rollerspeed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/instructores")
@Tag(name = "Instructores", description = "API para la gestión de instructores de la escuela de patinaje")
public class InstructorController {
    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping
    @Operation(summary = "Listar todos los instructores", description = "Obtiene una lista de todos los instructores registrados en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de instructores obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class)))
    })
    public List<Instructor> listar() {
        return instructorRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo instructor", description = "Crea un nuevo registro de instructor en el sistema con la información proporcionada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Instructor registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Instructor> registrar(
            @Parameter(description = "Datos del instructor a registrar", required = true)
            @Valid @RequestBody @NonNull Instructor instructor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorRepository.save(instructor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar instructor por ID", description = "Obtiene los detalles de un instructor específico mediante su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructor encontrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class))),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado con el ID proporcionado")
    })
    public Instructor buscarPorId(
            @Parameter(description = "ID del instructor a buscar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de un instructor", description = "Actualiza los datos de un instructor existente. Solo se modifican los campos proporcionados en el request body.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructor actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class))),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado con el ID proporcionado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public Instructor actualizar(
            @Parameter(description = "ID del instructor a actualizar", required = true, example = "1")
            @PathVariable @NonNull Long id,
            @Parameter(description = "Nuevos datos del instructor", required = true)
            @Valid @RequestBody @NonNull Instructor datos) {
        Instructor instructor = buscarPorId(id);
        instructor.setNombre(datos.getNombre());
        return instructorRepository.save(instructor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un instructor", description = "Elimina permanentemente un instructor del sistema mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Instructor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado con el ID proporcionado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del instructor a eliminar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado");
        }
        instructorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
