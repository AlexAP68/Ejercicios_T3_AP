package Activitat6.A1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerA61 implements Runnable {
    private Socket client;
    private boolean exit = false;

    public WorkerA61(Socket c) {
        client = c;
    }

    @Override
    public void run() {
        try {
            // Leer la entrada del cliente
            BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String linea;

            while ((linea = bf.readLine()) != null) {
                // Respuesta al cliente
                PrintWriter pw = new PrintWriter(client.getOutputStream());
                pw.write(linea.toUpperCase() + "\n");
                pw.flush();
            }

            client.close(); // Cerrar el socket al finalizar la comunicación
        } catch (IOException ex) {
            System.err.println("ClientWorker error.");
        }
    }
}