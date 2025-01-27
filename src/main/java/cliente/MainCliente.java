package cliente;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Punto de entrada del programa que maneja un cliente.
 * @author Daniel N. P.
 */
public class MainCliente {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // Imprimir inicio.
        System.out.println("┌──────────────────┐");
        System.out.println("│ passgen - client │");
        System.out.println("└──────────────────┘ - hecho por Daniel N. P.");
        System.out.println();
        System.out.println("Iniciando...");
        
        // Intentar crear un nuevo cliente conectándolo al servidor.
        try {
            Cliente cliente = new Cliente();
            cliente.interactua();
        } catch (IOException e) {
            System.err.println("No se pudo conectar al servidor. Abortando...");
        }
    }
}
