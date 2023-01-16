package org.ejemplos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pilotos")
public class Piloto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, unique = true)
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Equipo equipo;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PilotoDetalles detalles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Deporte> deportes;

    public Piloto() {

    }

    public Piloto(String nombre, LocalDate fechaNacimiento, Equipo equipo, PilotoDetalles detalles, List<Deporte> deportes) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
        this.detalles = detalles;
        this.deportes = deportes;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public PilotoDetalles getDetalles() {
        return detalles;
    }

    public void setDetalles(PilotoDetalles detalles) {
        this.detalles = detalles;
    }

    public List<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(List<Deporte> deportes) {
        this.deportes = deportes;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo=" + equipo +
                ", detalles=" + detalles +
                ", deportes=" + deportes +
                '}';
    }
}
