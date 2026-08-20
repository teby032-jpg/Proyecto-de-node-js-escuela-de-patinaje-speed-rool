package com.rollerspeed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    private final PagoRepository pagoRepository;

    public PagoController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @GetMapping
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@Valid @RequestBody @NonNull Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoRepository.save(pago));
    }

    @GetMapping("/{id}")
    public Pago buscarPorId(@PathVariable @NonNull Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado"));
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Pago datos) {
        Pago pago = buscarPorId(id);
        pago.setMonto(datos.getMonto());
        return pagoRepository.save(pago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado");
        }
        pagoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
