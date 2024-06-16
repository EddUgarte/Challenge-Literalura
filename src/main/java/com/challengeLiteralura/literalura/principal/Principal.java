package com.challengeLiteralura.literalura.principal;

import com.challengeLiteralura.literalura.model.Datos;
import com.challengeLiteralura.literalura.model.DatosLibros;
import com.challengeLiteralura.literalura.service.ConsumoAPI;
import com.challengeLiteralura.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private Scanner leer = new Scanner(System.in);

    public void muestraElMenu() {

        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println("Info de toooodo en json");
        System.out.println(json);

        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("Info de toooodo en mi Clase DatosLibros");
        System.out.println(datos);

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ******************      MENU      ****************
                    1 - Buscar Libro por titulo (en la API)
                    2 - Listar Libros registrados (en la Base de Datos)
                    3 - Listar Autores registrados (en la Base de Datos)
                    4 - Listar Autores vivos en un determinado año (en la Base de Datos)
                    5 - Listar Libros por Idioma (en la Base de Datos)
                                                      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = leer.nextInt();
            leer.nextLine(); //consumir el salto de linea

            switch (opcion) {
                case 1:
                    System.out.println("***Ha elegido Buscar Libro por Título***");
                    buscaLibroPorTitulo();
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
                    System.out.println("***Ha elegido listar Libros por Idioma***");
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    public void conectaConApiEnGeneral() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        //Tooodos los datos de tooodos los libros
        //System.out.println(json);

        var datos = conversor.obtenerDatos(json, Datos.class);
        //Datos especificos de toooodos los libros en la API
        System.out.println(datos);
    }

    public void buscaLibroPorTitulo() {

        //Busqueda de libros por nombre (o una parte de éste)
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        String tituloLibro = leer.nextLine();

        //Necesitamos actualizar el json para solicitar a la API
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ", "+"));

        //Ahora convierto ese JSON a mi clase
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()) {
            System.out.println("Libro encontrado!");
            System.out.println(libroBuscado.get()); //Para traer todos los datos
        } else {
            System.out.println("Libro no encontrado...");
        }

    }

}
