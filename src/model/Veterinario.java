package model;

import java.time.LocalDate;

public class Veterinario {
    private Integer id;
    private String numeroLicencia;
    private String nombre;
    private String apellido;
    private String especialidad;

    public Veterinario(String numeroLicencia, String nombre, String apellido, String especialidad) {
        this.numeroLicencia = numeroLicencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public Veterinario(Integer id, String numeroLicencia, String nombre, String apellido, String especialidad) {
        this.id = id;
        this.numeroLicencia = numeroLicencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", numeroLicencia='" + numeroLicencia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
