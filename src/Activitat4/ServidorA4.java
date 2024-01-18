package Activitat4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorA4 {

    public static void main(String[] args) {
        int puertoDestino = 2000;

        try (ServerSocket serverSocket = new ServerSocket(puertoDestino);
             Socket server = serverSocket.accept()) {

            System.out.println("Conexion recibida!");

            // Lee el mensaje del cliente
            InputStream is = server.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);
            String message = bf.readLine();

            // Prepara la informacion
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
                    System.out.println("Invalid operation!");
            }

            // Devuelve la informacion
            try (PrintWriter pw = new PrintWriter(server.getOutputStream())) {
                pw.println(result);
                pw.flush();
            }

        } catch (IOException e) {
            System.out.println("Error Server");
        }
    }
}
