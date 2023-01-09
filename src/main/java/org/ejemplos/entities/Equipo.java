package org.ejemplos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, unique = true)
    private String nombre;
    private String motor;
    @OneToMany(mappedBy = "equipo")
    private Set<Piloto> pilotos;

    public Equipo() {
    }

    public Equipo(String nombre, String motor) {
        this.nombre = nombre;
        this.motor = motor;
    }

    public Equipo(String nombre, String motor, Set<Piloto> pilotos) {
        this.nombre = nombre;
        this.motor = motor;
        this.pilotos = pilotos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Set<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(Set<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", motor='" + motor + '\'' +
                '}';
    }
}
