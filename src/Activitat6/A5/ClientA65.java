package Activitat6.A5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientA65 {
    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puertoServidor = 1500;

        try (Socket socket = new Socket(direccionServidor, puertoServidor)) {
            System.out.println("Conectado al servidor");

            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

            // Obtener el nombre del archivo del usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del archivo:");
            String nombreArchivo = scanner.nextLine();

            // Enviar el nombre del archivo al servidor
            escritor.println(nombreArchivo);

            // Recibir la respuesta del servidor
            String respuesta = lector.readLine();
            if (respuesta.equals("Archivo encontrado")) {
                recibirYMostrarArchivo(socket);
            } else {
                System.out.println("Error: " + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirYMostrarArchivo(Socket socket) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            InputStream flujoEntradaSocket = socket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = flujoEntradaSocket.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesLeidos);
            }

            // Imprimir el contenido del archivo por consola
            System.out.println("Contenido del archivo recibido:\n" + byteArrayOutputStream.toString("UTF-8"));
        }

        System.out.println("Archivo recibido exitosamente");
    }
}
