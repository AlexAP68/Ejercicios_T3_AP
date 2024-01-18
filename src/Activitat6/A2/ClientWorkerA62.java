package Activitat6.A2;

import java.io.*;
import java.net.Socket;

public class ClientWorkerA62 implements Runnable {

    private Socket clientSocket;
    private volatile boolean salir = false;

    public ClientWorkerA62(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            int numeroAleatorio = generarNumeroAleatorio();

            while (!salir) {
                // Leer del flujo de entrada
                InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bf = new BufferedReader(isr);

                String linea = bf.readLine();

                int numeroCliente = Integer.parseInt(linea);
                OutputStream os = clientSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);

                // Escribir en el flujo de salida
                if (numeroCliente < numeroAleatorio) {
                    pw.write("El número es mayor" + "\n");
                    pw.flush();
                } else if (numeroCliente > numeroAleatorio) {
                    pw.write("El número es menor" + "\n");
                    pw.flush();
                } else {
                    pw.write("¡El número es correcto!" + "\n");
                    pw.flush();
                    salir = true;
                }

                System.out.println(numeroAleatorio);
            }

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error en la conexión con el cliente");
        }
    }

    private int generarNumeroAleatorio() {
        return (int) (Math.random() * 100);
    }
}