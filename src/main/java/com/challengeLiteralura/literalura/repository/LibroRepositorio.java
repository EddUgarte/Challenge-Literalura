package com.challengeLiteralura.literalura.repository;

import java.util.List;
import com.challengeLiteralura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l WHERE l.autorFechaNacimiento < :anio AND l.autorFechaFallecimiento > :anio")
    List<Libro> encuentraAutoresPorAnio(Integer anio);

    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idioma")
    List<Libro> encuentraPorIdioma(String idioma);


}
