package com.challengeLiteralura.literalura.repository;

import java.util.List;
import com.challengeLiteralura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibroRepositorio extends JpaRepository<Libro,Long> {


}
