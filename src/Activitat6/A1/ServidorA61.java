package Activitat6.A1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorA61 {
    public static void main(String[] args) {
        int puertoDestino = 2222;

        try (ServerSocket serverSocket = new ServerSocket(puertoDestino)) {
            System.out.println("Esperando conexiones...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexion recibida!");

                // Crear un hilo para manejar la conexión del cliente
                Thread clientThread = new Thread(new WorkerA61(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor");
        }
    }
}