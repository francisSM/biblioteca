import java.util.*;

public class MenuInteractivo {
    private static final Map<String, Runnable> OPCIONES = new LinkedHashMap<>();

    static {
        OPCIONES.put("Agregar usuario", Main::agregarUsuario);
        OPCIONES.put("Eliminar usuario", Main::eliminarUsuario);
        OPCIONES.put("Modificar usuario", Main::editarusuario);
        OPCIONES.put("Agregar libro", Main::agregarLibro);
        OPCIONES.put("Eliminar libro", Main::eliminarLibro);
        OPCIONES.put("Realizar préstamo", Main::realizarPrestamo);
        OPCIONES.put("Realizar devolución", Main::realizarDevolucion);
        OPCIONES.put("Mostrar información de la Biblioteca", Main::mostrarInformacion);
        OPCIONES.put("Agregar sala", Main::agregarSala);
        OPCIONES.put("Eliminar sala", Main::eliminarSala);
        OPCIONES.put("Prestamo sala", Main::Prestamosala);
        OPCIONES.put("Devolver sala", Main::devolversala);
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("------ Menú ------");
            int i = 1;
            for (String opcion : OPCIONES.keySet()) {
                System.out.println(i + ". " + opcion);
                i++;
            }
            System.out.println(i + ". Salir");
            System.out.println("Seleccione una opción: ");

            try {
                int opcionSeleccionada = scanner.nextInt();
                scanner.nextLine();

                if (opcionSeleccionada >= 1 && opcionSeleccionada <= OPCIONES.size()) {
                    List<Runnable> listaOpciones = new ArrayList<>(OPCIONES.values());
                    Runnable opcion = listaOpciones.get(opcionSeleccionada - 1);
                    opcion.run();
                } else if (opcionSeleccionada == OPCIONES.size() + 1) {
                    salir = true; // arreglado
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
