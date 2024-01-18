package Activitat5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorA5 {

    public static void main(String[] args) {
        int puerto = 1500;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Conexión recibida desde " + clientSocket.getInetAddress());

                    BufferedReader lector = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter escritor = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Leer el nombre del archivo solicitado por el cliente
                    String nombreArchivo = lector.readLine();

                    // Verificar si el archivo existe
                    Path rutaArchivo = Paths.get(nombreArchivo);
                    if (Files.exists(rutaArchivo) && Files.isReadable(rutaArchivo) && !Files.isDirectory(rutaArchivo)) {
                        escritor.println("Archivo encontrado");
                        enviarArchivo(clientSocket, rutaArchivo);
                    } else {
                        escritor.println("Archivo no encontrado");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarArchivo(Socket clientSocket, Path rutaArchivo) throws IOException {
        try (BufferedInputStream flujoEntradaArchivo = new BufferedInputStream(new FileInputStream(rutaArchivo.toFile()))) {
            OutputStream flujoSalidaSocket = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = flujoEntradaArchivo.read(buffer)) != -1) {
                flujoSalidaSocket.write(buffer, 0, bytesLeidos);
            }
            flujoSalidaSocket.flush();
        }
    }
}
