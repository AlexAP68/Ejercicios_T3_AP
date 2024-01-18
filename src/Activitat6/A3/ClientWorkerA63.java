package Activitat6.A3;

import java.io.*;
import java.net.Socket;

public class ClientWorkerA63 implements Runnable {

    private Socket clientSocket;

    public ClientWorkerA63(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexión recibida desde " + clientSocket.getInetAddress());

            // Lee el JSON del flujo de entrada
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);
            String jsonStr = bf.readLine();

            // Guarda el JSON en un archivo
            try (FileWriter fileWriter = new FileWriter("Activitat3.json")) {
                fileWriter.write(jsonStr);
                System.out.println("JSON guardado en Activitat3.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Envía respuesta al cliente
            OutputStream os = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("ok".toUpperCase() + "\n");
            pw.flush();

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error en la conexión con el cliente");
        }
    }
}
