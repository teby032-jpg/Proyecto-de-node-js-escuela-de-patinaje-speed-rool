package com.rollerspeed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/instructores")
public class InstructorController {
    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping
    public List<Instructor> listar() {
        return instructorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Instructor> registrar(@Valid @RequestBody @NonNull Instructor instructor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorRepository.save(instructor));
    }

    @GetMapping("/{id}")
    public Instructor buscarPorId(@PathVariable @NonNull Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado"));
    }

    @PutMapping("/{id}")
    public Instructor actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Instructor datos) {
        Instructor instructor = buscarPorId(id);
        instructor.setNombre(datos.getNombre());
        return instructorRepository.save(instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado");
        }
        instructorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
