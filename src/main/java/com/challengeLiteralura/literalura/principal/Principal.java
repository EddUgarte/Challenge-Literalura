package com.challengeLiteralura.literalura.principal;

import com.challengeLiteralura.literalura.model.Datos;
import com.challengeLiteralura.literalura.model.DatosLibros;
import com.challengeLiteralura.literalura.service.ConsumoAPI;
import com.challengeLiteralura.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private Scanner leer = new Scanner(System.in);

    public void muestraElMenu() {

        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(json);

        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
        //******************************************
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libro por titulo
                    2 - Listar Libros registrados en la Base de Datos
                    3 - Listar Autores registrados en la Base de Datos
                    4 - Listar Autores vivos en un determinado año
                    5 - Listar Libros por Idioma
                                                      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = leer.nextInt();
            leer.nextLine(); //consumir el salto de linea

            switch (opcion) {
                case 1:
                    System.out.println("Buscar Libro por titulo");
                    break;
                case 2:
                    System.out.println("Listar Libros registrados en la Base de Datos");
                    break;
                case 3:
                    System.out.println("Listar Autores registrados en la Base de Datos");
                    break;
                case 4:
                    System.out.println("Listar Autores vivos en un determinado año");
                    break;
                case 5:
                    System.out.println("Listar Libros por Idioma");
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

}
