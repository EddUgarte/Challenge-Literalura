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
    private String idiomas;
    private Double descargas;
    private String autorNombre;
    private Integer autorFechaNacimiento;
    private Integer autorFechaFallecimiento;

    //Constructores
    public Libro(){}

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas().getFirst();
        this.descargas = datosLibros.numeroDeDescargas();
        this.autorNombre = datosLibros.autor().getFirst().nombre();
        this.autorFechaNacimiento = datosLibros.autor().getFirst().fechaDeNacimiento();
        this.autorFechaFallecimiento = datosLibros.autor().getFirst().fechaDeFallecimiento();
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

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

    public Integer getAutorFechaNacimiento() {
        return autorFechaNacimiento;
    }

    public void setAutorFechaNacimiento(Integer autorFechaNacimiento) {
        this.autorFechaNacimiento = autorFechaNacimiento;
    }

    public Integer getAutorFechaFallecimiento() {
        return autorFechaFallecimiento;
    }

    public void setAutorFechaFallecimiento(Integer autorFechaFallecimiento) {
        this.autorFechaFallecimiento = autorFechaFallecimiento;
    }

    //toString

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idiomas + '\'' +
                ", descargas=" + descargas +
                ", autorNombre='" + autorNombre + '\'' +
                ", autorFechaNacimiento=" + autorFechaNacimiento +
                ", autorFechaFallecimiento=" + autorFechaFallecimiento +
                '}';
    }
}
