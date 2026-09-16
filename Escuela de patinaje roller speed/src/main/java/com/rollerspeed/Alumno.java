package com.rollerspeed;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Schema(description = "Representa un alumno de la escuela de patinaje Roller Speed")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del alumno", example = "1")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre completo del alumno", example = "Juan Pérez", required = true)
    private String nombre;
    
    @Email(message = "El email no tiene un formato válido")
    @Schema(description = "Correo electrónico del alumno", example = "juan.perez@email.com")
    private String email;
    
    @Schema(description = "Número de teléfono del alumno", example = "3001234567")
    private String telefono;
    
    @Min(value = 3, message = "La edad mínima es 3 años")
    @Schema(description = "Edad del alumno en años", example = "15", minimum = "3")
    private Integer edad;
    
    @Schema(description = "Nivel de patinaje del alumno", example = "Avanzado")
    private String nivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
