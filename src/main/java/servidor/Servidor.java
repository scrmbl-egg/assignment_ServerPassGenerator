package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Clase de Servidor.
 * @author Daniel N. P.
 */
public class Servidor {
    /**
     * Puerto al que se podrán unir los clientes.
     */
    private static final int PUERTO = 4321;
    
    /**
     * Instancia de ServicioPass.
     */
    private ServicioPass servicioPass;
    
    /**
     * Instancia de ServerSocket desde el que aceptaremos sockets de clientes.
     */
    private ServerSocket serverSocket;
    
    /**
     * Instancia de Socket aceptado.
     */
    private Socket socket;
    
    /**
     * Constructor en el que se inicializa el servidor con sus sockets.
     * @throws IOException
     */
    public Servidor() throws IOException {
        serverSocket = new ServerSocket(PUERTO);
        socket = new Socket();
    }
    
    /**
     * Comienza la ejecución del servidor
     * @throws IOException
     */
    public void start() throws IOException {
        while (true) {
            manejarProximoCliente();
        }
    }
    
    /**
     * Maneja la conexión e interacciones de un cliente que se ha conectado.
     * @throws IOException
     */
    private void manejarProximoCliente() throws IOException {
        try {
            // Esperar nuevo socket.
            System.out.println("Esperando conexión del cliente...");
            socket = serverSocket.accept();
            
            // Crear streams de entrada y salida a partir del socket aceptado.
            DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream socketIn = new DataInputStream(socket.getInputStream());
            
            // Confirmar conexión y dar bienvenida al usuario.
            System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
            socketOut.writeUTF(
                "[Servidor] Te has conectado al servidor. ¡Bienvenido!\n"
                + "[Servidor] ¿Cómo te llamas?"
            );
            
            // Esperar entrada del cliente
            String nombreCliente = socketIn.readUTF();
            
            // Saludar y preguntar mayúsculas
            System.out.println("Nombre de cliente: " + nombreCliente + ".");
            System.out.println("Obteniendo requisitos de contraseña...");
            socketOut.writeUTF(
                "[Servidor] Hola, " + nombreCliente + ".\n"
                + "[Servidor] Responde a las siguientes preguntas para generar tu "
                    + "nueva contraseña.\n"
                + "[Servidor] ¿Cuántas letras mayúsculas deseas en tu contraseña? ["
                    + RequisitosPass.MINIMO_INCLUSIVO + " - "
                    + RequisitosPass.MAXIMO_INCLUSIVO + "]"
            );
                
            // Obtener número de mayúsculas.
            int numMayusculas = socketIn.readInt();

            // Preguntar minúsculas.
            socketOut.writeUTF(
                "[Servidor] ¿Cuántas letras minúsculas deseas en tu contraseña? ["
                    + RequisitosPass.MINIMO_INCLUSIVO + " - "
                    + RequisitosPass.MAXIMO_INCLUSIVO + "]"
            );
            
            // Obtener número de minúsculas.
            int numMinusculas = socketIn.readInt();
            
            // Preguntar número de dígitos.
            socketOut.writeUTF(
                "[Servidor] ¿Cuántos dígitos deseas en tu contraseña? ["
                    + RequisitosPass.MINIMO_INCLUSIVO + " - "
                    + RequisitosPass.MAXIMO_INCLUSIVO + "]"
            );
            
            // Obtener número de dígitos.
            int numDigitos = socketIn.readInt();
            
            // Preguntar número de caracteres especiales
            socketOut.writeUTF(
                "[Servidor] ¿Cuántos caracteres especiales ("
                    + RequisitosPass.CARACTERES_ESPECIALES
                    + ") deseas en tu contraseña? ["
                    + RequisitosPass.MINIMO_INCLUSIVO + " - "
                    + RequisitosPass.MAXIMO_INCLUSIVO + "]"
            );
            
            // Obtener número de caracteres especiales.
            int numCaractEspeciales = socketIn.readInt();
            
            // Inicializar el servicio de contraseñas.
            servicioPass = new ServicioPass(new RequisitosPass(
                numMayusculas, 
                numMinusculas, 
                numDigitos, 
                numCaractEspeciales
            ));
            
            // Imprimir objeto creado en consola
            System.out.println(
                nombreCliente + " (" + socket.getInetAddress() + ") ha generado "
                    + "el siguiente objeto de requisitos de contraseña:\n"
                + servicioPass.getRequisitosPass()
            );
            
            // Enviar un diagnóstico detallado de los requisitos resultantes
            socketOut.writeUTF(
                "[Servidor] Estos son los requisitos que has especificado:\n"
                + "\tNúmero de mayúsculas: " + numMayusculas + ",\n"
                + "\tNúmero de minúsculas: " + numMinusculas + ",\n"
                + "\tNúmero de dígitos: " + numDigitos + ",\n"
                + "\tNúmero de caracteres especiales: " + numCaractEspeciales + ".\n\n"
                + "\tLongitud de contraseña: " + servicioPass.longitudPass() + ".\n"
                + "[Servidor] ¿Deseas generar la contraseña? [S / N]"
            );
            
            // Obtener respuesta de usuario
            String respuesta = socketIn.readUTF();
            
            // Manejar respuestas.
            if (respuesta.equals("S")) {
                System.out.println("Generando contraseña...");
                
                String pass = servicioPass.generaPass();
                socketOut.writeUTF(
                    "[Servidor] Esta es tu contraseña: " + pass + "\n"
                    + "[Servidor] Desconectando..."
                );
                
                System.out.println("Desconectando...");
            } else if (respuesta.equals("N")) {
                System.out.println("Desconectando...");
                socketOut.writeUTF("[Servidor] Desconectando...");
            }
            
        } catch (SocketException e) {
            // Mensaje para cuando se cierre la aplicación de cliente.
            System.err.println("El cliente se ha desconectado de manera repentina.");
        } finally {         
            System.out.println("Conexión terminada.");
        }
    }
}
