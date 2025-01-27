package servidor;

import java.io.IOException;

/**
 * Punto de entrada del programa que maneja el servidor.
 * @see Servidor
 * @author Daniel N. P.
 */
public class MainServidor {
    public static void main(String[] args) throws IOException {
        // Imprimir inicio.
        System.out.println("╔══════════════════╗");
        System.out.println("║ passgen - server ║");
        System.out.println("╚══════════════════╝ - hecho por Daniel N. P.");
        System.out.println();
        System.out.println("Iniciando...");
        
        // Iniciar servidor
        Servidor servidor = new Servidor();
        servidor.start();
    }
}
