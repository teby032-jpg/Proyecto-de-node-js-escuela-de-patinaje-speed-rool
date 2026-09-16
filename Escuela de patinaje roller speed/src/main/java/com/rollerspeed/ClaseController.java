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
@RequestMapping("/clases")
@Tag(name = "Clases", description = "API para la gestión de clases de la escuela de patinaje")
public class ClaseController {
    private final ClaseRepository claseRepository;

    public ClaseController(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @GetMapping
    @Operation(summary = "Listar todas las clases", description = "Obtiene una lista de todas las clases registradas en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clases obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class)))
    })
    public List<Clase> listar() {
        return claseRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva clase", description = "Crea un nuevo registro de clase en el sistema con la información proporcionada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Clase registrada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Clase> registrar(
            @Parameter(description = "Datos de la clase a registrar", required = true)
            @Valid @RequestBody @NonNull Clase clase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claseRepository.save(clase));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar clase por ID", description = "Obtiene los detalles de una clase específica mediante su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase encontrada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada con el ID proporcionado")
    })
    public Clase buscarPorId(
            @Parameter(description = "ID de la clase a buscar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de una clase", description = "Actualiza los datos de una clase existente. Solo se modifican los campos proporcionados en el request body.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase actualizada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada con el ID proporcionado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public Clase actualizar(
            @Parameter(description = "ID de la clase a actualizar", required = true, example = "1")
            @PathVariable @NonNull Long id,
            @Parameter(description = "Nuevos datos de la clase", required = true)
            @Valid @RequestBody @NonNull Clase datos) {
        Clase clase = buscarPorId(id);
        clase.setNombre(datos.getNombre());
        return claseRepository.save(clase);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una clase", description = "Elimina permanentemente una clase del sistema mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Clase eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada con el ID proporcionado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la clase a eliminar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        if (!claseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no encontrada");
        }
        claseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
