package com.rollerspeed;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoRepository alumnoRepository;

    public AlumnoController(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping
    public List<Alumno> listar(@RequestParam(required = false) String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return alumnoRepository.findAll();
        }
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre.trim());
    }

    @PostMapping
    public ResponseEntity<Alumno> registrar(@Valid @RequestBody @NonNull Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoRepository.save(alumno));
    }

    @GetMapping("/{id}")
    public Alumno buscarPorId(@PathVariable @NonNull Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    @PutMapping("/{id}")
    public Alumno actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Alumno datos) {
        Alumno alumno = buscarPorId(id);
        alumno.setNombre(datos.getNombre());
        alumno.setEmail(datos.getEmail());
        alumno.setTelefono(datos.getTelefono());
        alumno.setEdad(datos.getEdad());
        alumno.setNivel(datos.getNivel());
        return alumnoRepository.save(alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
        alumnoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
