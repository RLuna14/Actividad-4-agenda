import java.io.*;
import java.util.*;

class AdressBook {

    private HashMap<String, String> contactos; // Mapa para almacenar contactos (número:nombre)

    // Constructor
    public AdressBook() {
        contactos = new HashMap<>();
        // Agregar contactos predeterminados
        contactos.put("3338205918", "Alan Rafael");
        contactos.put("4434003109", "Victor Camacho");
        contactos.put("3339659501", "Eduardo Aparicio");
        contactos.put("3334054879", "Kevin Mandujano");
    }

    // Método para cargar contactos del archivo
    public void load(String archivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    contactos.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar contactos: " + e.getMessage());
        }
    }

    // Método para guardar contactos en el archivo
    public void save(String archivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<String, String> entrada : contactos.entrySet()) {
                escritor.write(entrada.getKey() + ":" + entrada.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    // Método para mostrar la lista de contactos
    public void list() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("Contactos:");
            for (Map.Entry<String, String> entrada : contactos.entrySet()) {
                System.out.println(entrada.getKey() + " : " + entrada.getValue());
            }
        }
    }

    // Método para crear un nuevo contacto
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono: ");
        String numero = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        if (contactos.containsKey(numero)) {
            System.out.println("El número de teléfono ya existe.");
        } else {
            contactos.put(numero, nombre);
            System.out.println("Contacto creado exitosamente.");
        }
    }

    // Método para eliminar un contacto
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono a eliminar: ");
        String numero = scanner.nextLine();

        if (contactos.containsKey(numero)) {
            contactos.remove(numero);
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("El número de teléfono no existe.");
        }
    }

    // Método para mostrar el menú principal
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de la Agenda Telefónica:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    list();
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    System.out.println("Saliendo de la agenda...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        AdressBook agenda = new AdressBook();
        agenda.load("contactos.txt"); // Cargar contactos del archivo

        agenda.menu(); // Mostrar menú principal

        agenda.save("contactos.txt"); // Guardar cambios en el archivo antes de salir
    }
}
