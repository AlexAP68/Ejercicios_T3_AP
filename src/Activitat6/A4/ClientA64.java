package Activitat6.A4;

import Activitat4.ClientA4;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientA64 {

    public static BufferedReader getFlujo(InputStream is) {
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) {
        String destino = "localhost";
        int puertoDestino = 2000;
        try (Socket socket = new Socket()) {
            InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

            Scanner sc = new Scanner(System.in);

            //elige una operacion
            System.out.println("Elige operacion (suma, resta, multiplica, divide):");
            String operation = sc.nextLine().toLowerCase();

            if (!isValidOperation(operation)) {
                System.out.println("Error: Operación no válida.");
                return;
            }
            //elige un numero
            System.out.println("Introduce el primer numero:");
            double num1 = getValidNumber(sc);

            System.out.println("Introduce el segundo numero:");
            double num2 = getValidNumber(sc);

            double result = 0.0;

            // Prepara el mensaje para enviarlo al server
            String message = operation + "," + num1 + "," + num2;

            try {
                socket.connect(direccion);
                while (true) {
//Envia y recibe el resultado
                    PrintWriter pw = new PrintWriter(socket.getOutputStream());
                    pw.print(message + "\n");
                    pw.flush();
                    BufferedReader bfr = ClientA64.getFlujo(socket.getInputStream());
                    System.out.println("El resultado fue: " + bfr.readLine());
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error Client");
            }

        } catch (IOException e) {
            System.out.println("Error Client");
        }
    }

    private static boolean isValidOperation(String operation) {
        return operation.equals("suma") || operation.equals("resta") || operation.equals("multiplica") || operation.equals("divide");
    }

    private static double getValidNumber(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Error: Ingresa un número válido.");
            sc.next(); // Consumir entrada no válida
        }
        return sc.nextDouble();
    }
}