package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import servidor.RequisitosPass;

/**
 * Clase que representa una instancia de un cliente.
 * @author Daniel N. P.
 */
public class Cliente {
    /**
     * Host al que va a conectar el cliente.
     */
    private static final String HOST = "localhost";
    
    /**
     * Puerto al que va a conectar el cliente.
     */
    private static final int PUERTO = 4321;
    
    /**
     * Socket del cliente.
     */
    private Socket socket;
    
    /**
     * Crea un nuevo cliente, conectando su socket.
     * @throws UnknownHostException
     * @throws IOException
     */
    public Cliente() throws UnknownHostException, IOException {
        socket = new Socket(HOST, PUERTO);
    }
    
    /**
     * Maneja las interacciones de entrada y salida del socket del cliente.
     * @throws IOException
     */
    public void interactua() throws IOException {
        // Inicializar entrada y salida del socket, junto con un escáner para entrada del usuario.
        DataInputStream socketIn = new DataInputStream(socket.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        
        // El servidor da la bienvenida al cliente y pregunta el nombre del usuario.
        System.out.println(socketIn.readUTF());
        // Dar nombre.
        enviarNombre(scanner, socketOut);
        
        // Imprimir saludo y pregunta de mayúsculas
        System.out.println(socketIn.readUTF());
        // Dar número de mayúsculas.
        enviarNumero(scanner, socketOut);
        
        // Imprimir pregunta de minúsculas.
        System.out.println(socketIn.readUTF());
        // Dar número de minúsculas.
        enviarNumero(scanner, socketOut);
        
        // Imprimir pregunta de dígitos.
        System.out.println(socketIn.readUTF());
        // Dar número de dígitos
        enviarNumero(scanner, socketOut);
        
        // Imprimir pregunta de caracteres especiales.
        System.out.println(socketIn.readUTF());
        // Dar número de caracteres especiales.
        enviarNumero(scanner, socketOut);
        
        // Imprimir requisitos generados y pregunta de si quiere generar la contraseña.
        System.out.println(socketIn.readUTF());
        // Dar respuesta de sí o no;
        enviarSiONo(scanner, socketOut);
        
        // Imprimir respuesta del servidor
        System.out.println(socketIn.readUTF());
        
        // Liberar recursos y socket.
        scanner.close();
        socketOut.close();
        socketIn.close();
        socket.close();
    }
    
    /**
     * Envía el nombre del usuario a la salida del socket.
     * @param scanner Escáner para la entrada del usuario.
     * @param out Salida a la que enviar el resultado.
     * @throws IOException
     */
    private void enviarNombre(Scanner scanner, DataOutputStream out) throws IOException {
        while (true) {
            String nombreCliente = scanner.nextLine();
            if (nombreCliente.isBlank()) {
                continue;
            }
            
            // Enviar nombre en socketOut
            out.writeUTF(nombreCliente);
            break;
        }
    }
    
    /**
     * Envía un número del usuario a la salida del socket.
     * @param scanner Escáner para la entrada del usuario.
     * @param out Salida a la que enviar el resultado.
     * @throws IOException
     */
    private void enviarNumero(Scanner scanner, DataOutputStream out) throws IOException {
        while (true) {
            int numero;
            
            // Validar si la entrada del usuario va a ser un número.
            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            
            numero = scanner.nextInt();
            
            boolean numeroEsValido = 
                    numero >= RequisitosPass.MINIMO_INCLUSIVO
                    && numero <= RequisitosPass.MAXIMO_INCLUSIVO;
                    
            if (!numeroEsValido) {
                continue;
            }
            
            out.writeInt(numero);
            break;
        }
    }
    
    /**
     * Envía "S" o "N" del usuario a la salida del socket.
     * @param scanner Escáner para la entrada del usuario.
     * @param out Salida a la que enviar el resultado.
     * @throws IOException
     */
    private void enviarSiONo(Scanner scanner, DataOutputStream out) throws IOException {
        while (true) {
            String respuesta = scanner.nextLine();
            
            boolean respuestaEsValida =
                    respuesta.trim().toUpperCase().equals("S")
                    || respuesta.trim().toUpperCase().equals("N");
            
            if (!respuestaEsValida) {
                //System.out.println(respuesta.trim().toUpperCase());
                continue;
            }
            
            // Enviar respuesta en socketOut
            out.writeUTF(respuesta.trim().toUpperCase());
            break;
        }
    }
}
