package com.challengeLiteralura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idiomas;
    private Double descargas;

    //Constructores
    public Libro(){}

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas().getFirst();
        this.descargas = datosLibros.numeroDeDescargas();
    }

    //Getter y Setter

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    //toString

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas='" + idiomas + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
