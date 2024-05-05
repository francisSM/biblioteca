import java.util.*;

public class MenuInteractivo {
    private static final Map<String, Map<String, Runnable>> OPCIONES = new LinkedHashMap<>();

    static {
        Map<String, Runnable> gestionUsuarios = new LinkedHashMap<>();
        gestionUsuarios.put("Agregar usuario", Main::agregarUsuario);
        gestionUsuarios.put("Eliminar usuario", Main::eliminarUsuario);
        gestionUsuarios.put("Modificar usuario", Main::editarusuario);
        OPCIONES.put("Gestionar usuarios", gestionUsuarios);

        Map<String, Runnable> gestionLibros = new LinkedHashMap<>();
        gestionLibros.put("Agregar libro", Main::agregarLibro);
        gestionLibros.put("Eliminar libro", Main::eliminarLibro);
        OPCIONES.put("Gestionar libros", gestionLibros);

        Map<String, Runnable> gestionPrestamosLibros = new LinkedHashMap<>();
        gestionPrestamosLibros.put("Realizar préstamo", Main::realizarPrestamo);
        gestionPrestamosLibros.put("Realizar devolución", Main::realizarDevolucion);
        OPCIONES.put("Gestionar préstamos libros", gestionPrestamosLibros);

        Map<String, Runnable> gestionPrestamosSala = new LinkedHashMap<>();
        gestionPrestamosSala.put("Agregar Sala", Main::agregarSala);
        gestionPrestamosSala.put("Eliminar Sala", Main::eliminarSala);
        gestionPrestamosSala.put("Prestamo sala", Main::prestamoSala);
        gestionPrestamosSala.put("Devolver sala", Main::devolverSala);
        OPCIONES.put("Gestionar préstamos sala", gestionPrestamosSala);

        Map<String, Runnable> mostrarInformacion = new LinkedHashMap<>();
        mostrarInformacion.put("Información de usuarios", Main::mostrarInformacionUsuarios);
        mostrarInformacion.put("Información de libros", Main::mostrarInformacionLibros);
        mostrarInformacion.put("Información de salas", Main::mostrarInformacionSalas);
        mostrarInformacion.put("Historial de préstamos de libros", Main::mostrarHistorialPrestamosLibros);
        OPCIONES.put("Mostrar información", mostrarInformacion);
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú");
            int i = 1;
            for (String categoria : OPCIONES.keySet()) {
                System.out.println(i + ". " + categoria);
                i++;
            }
            System.out.println(i + ". Salir");
            System.out.println("Seleccione una opción: ");

            try {
                int opcionCategoria = scanner.nextInt();
                scanner.nextLine();

                if (opcionCategoria >= 1 && opcionCategoria <= OPCIONES.size()) {
                    List<Map<String, Runnable>> listaCategorias = new ArrayList<>(OPCIONES.values());
                    Map<String, Runnable> categoriaSeleccionada = listaCategorias.get(opcionCategoria - 1);

                    boolean salirCategoria = false;
                    while (!salirCategoria) {
                        System.out.println(OPCIONES.keySet().toArray()[opcionCategoria - 1]);
                        int j = 1;
                        for (String opcion : categoriaSeleccionada.keySet()) {
                            System.out.println(j + ". " + opcion);
                            j++;
                        }
                        System.out.println(j + ". Volver");
                        System.out.println("Seleccione una opción: ");

                        int opcionSeleccionada = scanner.nextInt();
                        scanner.nextLine();

                        if (opcionSeleccionada >= 1 && opcionSeleccionada <= categoriaSeleccionada.size()) {
                            List<Runnable> listaOpciones = new ArrayList<>(categoriaSeleccionada.values());
                            Runnable opcion = listaOpciones.get(opcionSeleccionada - 1);
                            opcion.run();
                        } else if (opcionSeleccionada == categoriaSeleccionada.size() + 1) {
                            salirCategoria = true;
                        } else {
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        }
                    }
                } else if (opcionCategoria == OPCIONES.size() + 1) {
                    salir = true;
                } else {
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número asociado a las siguientes opciones:");
                scanner.nextLine();
            }
        }

        System.out.println("Programa finalizado");
        scanner.close();
    }
}
