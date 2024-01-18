package Activitat6.A2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorA62 {
    public static void main(String[] args) {
        int puertoDestino = 2222;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            ServerSocket serverSocket = new ServerSocket(puertoDestino);
            System.out.println("Servidor esperando conexiones en el puerto " + puertoDestino);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión recibida!");

                executorService.execute(new ClientWorkerA62(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor");
        } finally {
            executorService.shutdown();
        }
    }
}