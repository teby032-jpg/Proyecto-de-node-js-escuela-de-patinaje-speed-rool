package com.rollerspeed;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	List<Alumno> findByNombreContainingIgnoreCase(String nombre);
}
