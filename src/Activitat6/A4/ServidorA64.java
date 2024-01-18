package Activitat6.A4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorA64 {

    public static void main(String[] args) {
        int puertoDestino = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(puertoDestino)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puertoDestino);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientWorkerA64(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor");
        } finally {
            executorService.shutdown();
        }
    }
}
