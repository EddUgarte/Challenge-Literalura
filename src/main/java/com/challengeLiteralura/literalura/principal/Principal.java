package com.challengeLiteralura.literalura.principal;

import com.challengeLiteralura.literalura.model.Datos;
import com.challengeLiteralura.literalura.model.DatosLibros;
import com.challengeLiteralura.literalura.model.Libro;
import com.challengeLiteralura.literalura.repository.LibroRepositorio;
import com.challengeLiteralura.literalura.service.ConsumoAPI;
import com.challengeLiteralura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private Scanner leer = new Scanner(System.in);
    private LibroRepositorio repositorioLibro;

    //Constructores *********
    public Principal(LibroRepositorio repositorioLibro) {
        this.repositorioLibro = repositorioLibro;
    }

    // **********************

    public void muestraElMenu() {

        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("---Info de toooodo en formato DatosLibros---");
        System.out.println(datos);

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ******************      MENU      ****************
                    1 - Buscar Libro por titulo (en la API) y guardarlo en la BD
                    2 - Listar Libros registrados (de la Base de Datos)
                    3 - Listar Autores registrados (de la Base de Datos)
                    4 - Listar Autores vivos en un determinado año (de la Base de Datos)
                    5 - Listar Libros por Idioma (de la Base de Datos)
                                                      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = leer.nextInt();
            leer.nextLine(); //consumir el salto de linea

            switch (opcion) {
                case 1:
                    System.out.println("***Ha elegido Buscar Libro por Título y guardarlo***");
                    buscarLibroApi();
                    break;
                case 2:
                    System.out.println("***Ha elegido listar Libros registrados en la Base de Datos***");
                    listarLibrosBd();
                    break;
                case 3:
                    System.out.println("***Ha elegido listar Autores registrados en la Base de Datos***");
                    listarAutoresBd();
                    break;
                case 4:
                    System.out.println("***Ha elegido listar Autores vivos en un determinado año***");
                    listarAutoresPorAnio();
                    break;
                case 5:
                    System.out.println("***Ha elegido listar Libros por Idioma***");
                    listarAutoresPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void listarAutoresPorIdioma() {
        System.out.println("Selecciona el idioma: ");
        System.out.println("1-Español   2-Inglés   3-Francés");
        Integer opIdioma = leer.nextInt();
        leer.nextLine();// absorber salto de linea

    }

    private void listarAutoresPorAnio() {
        System.out.println("Ingresa el año: ");
        Integer anioBuscar = leer.nextInt();
        leer.nextLine();// absorber salto de linea

    }

    private void listarAutoresBd() {
        List<Libro> todosLosLibros = new ArrayList<>();
        todosLosLibros = repositorioLibro.findAll();
        Set<Libro> librosSinRepetir = new HashSet<>(todosLosLibros);
        System.out.println("Listando todos los Autores de la BD: ");
        for (Libro aux : librosSinRepetir) {
            System.out.println("Autor: " + aux.getAutorNombre());
        }
    }

    private void listarLibrosBd() {
        List<Libro> todosLosLibros = new ArrayList<>();
        todosLosLibros = repositorioLibro.findAll();
        todosLosLibros.forEach(System.out::println);
    }

    //Metodo para buscar un libro en API y persistirlo en la BD
    private void buscarLibroApi() {
        DatosLibros datosLibro = getDatosLibro();
        if (datosLibro == null) {
            System.out.println("El libro no se encontró o ya está en la BD...");
        }
        Libro libro = new Libro(datosLibro);
        repositorioLibro.save(libro);
        System.out.println(libro);
    }

    public DatosLibros getDatosLibro() {

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
            DatosLibros lib1 = libroBuscado.get();
            return lib1;
        } else {
            System.out.println("Libro no encontrado...");
        }
        return null;
    }

}
