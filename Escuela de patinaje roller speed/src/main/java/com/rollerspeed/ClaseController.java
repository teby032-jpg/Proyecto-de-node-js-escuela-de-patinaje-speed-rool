package com.rollerspeed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clases")
public class ClaseController {
    private final ClaseRepository claseRepository;

    public ClaseController(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @GetMapping
    public List<Clase> listar() {
        return claseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Clase> registrar(@Valid @RequestBody @NonNull Clase clase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claseRepository.save(clase));
    }

    @GetMapping("/{id}")
    public Clase buscarPorId(@PathVariable @NonNull Long id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no encontrada"));
    }

    @PutMapping("/{id}")
    public Clase actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Clase datos) {
        Clase clase = buscarPorId(id);
        clase.setNombre(datos.getNombre());
        return claseRepository.save(clase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        if (!claseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no encontrada");
        }
        claseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
