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
@RequestMapping("/pagos")
@Tag(name = "Pagos", description = "API para la gestión de pagos de la escuela de patinaje")
public class PagoController {
    private final PagoRepository pagoRepository;

    public PagoController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene una lista de todos los pagos registrados en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class)))
    })
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago", description = "Crea un nuevo registro de pago en el sistema con la información proporcionada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Pago> registrar(
            @Parameter(description = "Datos del pago a registrar", required = true)
            @Valid @RequestBody @NonNull Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoRepository.save(pago));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID", description = "Obtiene los detalles de un pago específico mediante su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado con el ID proporcionado")
    })
    public Pago buscarPorId(
            @Parameter(description = "ID del pago a buscar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de un pago", description = "Actualiza los datos de un pago existente. Solo se modifican los campos proporcionados en el request body.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado con el ID proporcionado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public Pago actualizar(
            @Parameter(description = "ID del pago a actualizar", required = true, example = "1")
            @PathVariable @NonNull Long id,
            @Parameter(description = "Nuevos datos del pago", required = true)
            @Valid @RequestBody @NonNull Pago datos) {
        Pago pago = buscarPorId(id);
        pago.setMonto(datos.getMonto());
        return pagoRepository.save(pago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago", description = "Elimina permanentemente un pago del sistema mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pago eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado con el ID proporcionado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del pago a eliminar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado");
        }
        pagoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
