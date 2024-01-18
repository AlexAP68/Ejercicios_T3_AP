package Activitat6.A5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorA65 {
    public static void main(String[] args) {
        int puerto = 1500;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(new ClientWorkerA65(clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}