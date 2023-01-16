package org.ejemplos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pilotos_detalles")
public class PilotoDetalles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (unique = true)
    private int numero;
    @Column (length = 30)
    private String nacionalidad;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Piloto piloto;

    public PilotoDetalles() {

    }
    public PilotoDetalles(int numero, String nacionalidad) {
        this.numero = numero;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    @Override
    public String toString() {
        return "PilotoDetalles{" +
                "id=" + id +
                ", numero=" + numero +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
