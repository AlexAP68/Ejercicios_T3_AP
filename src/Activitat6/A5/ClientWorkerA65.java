package Activitat6.A5;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientWorkerA65 implements Runnable {

    private Socket clientSocket;

    public ClientWorkerA65(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter escritor = new PrintWriter(clientSocket.getOutputStream(), true)) {

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

    private static void enviarArchivo(Socket clientSocket, Path rutaArchivo) {
        try (BufferedReader lectorArchivo = Files.newBufferedReader(rutaArchivo);
             PrintWriter escritor = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String linea;
            while ((linea = lectorArchivo.readLine()) != null) {
                escritor.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}