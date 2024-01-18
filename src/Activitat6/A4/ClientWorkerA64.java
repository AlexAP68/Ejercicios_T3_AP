package Activitat6.A4;

import java.io.*;
import java.net.Socket;

public class ClientWorkerA64 implements Runnable {

    private Socket clientSocket;

    public ClientWorkerA64(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexión recibida desde " + clientSocket.getInetAddress());

            // Lee el mensaje del cliente
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);
            String message = bf.readLine();

            // Parsea el mensaje y realiza el cálculo
            String[] parts = message.split(",");
            String operation = parts[0];
            double num1 = Double.parseDouble(parts[1]);
            double num2 = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operation) {
                case "suma":
                    result = num1 + num2;
                    break;
                case "resta":
                    result = num1 - num2;
                    break;
                case "multiplica":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("¡No se puede dividir por cero!");
                    }
                    break;
                default:
                    System.out.println("Operación no válida!");
            }

            // Envía el resultado al cliente
            try (PrintWriter pw = new PrintWriter(clientSocket.getOutputStream())) {
                pw.println(result);
                pw.flush();
            }

            // Cierra la conexión con el cliente
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error en la conexión con el cliente");
        }
    }
}