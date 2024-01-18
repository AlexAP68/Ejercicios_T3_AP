package Activitat3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorA3 {
    static boolean exit = false;
    public static void main(String[] args) {
        int puertoDestino = 2222;
        try {
            ServerSocket serverSocket = new ServerSocket(puertoDestino);
            Socket server = serverSocket.accept();
            while (!exit) {
                System.out.println("Conexion recibida!");
//Read From Stream
                InputStream is = server.getInputStream();
                InputStreamReader isr

                        = new InputStreamReader(is);
                BufferedReader bf
                        = new BufferedReader(isr);
                String linea = bf.readLine();

//Write In Stream
                try( FileWriter fileWriter = new FileWriter("Activitat3.json");) {
                    fileWriter.write(linea);
                    System.out.println("hecho");


                }catch (IOException e) {
                    e.printStackTrace();
                }

                OutputStream os = server.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("ok".toUpperCase() + "\n");
                pw.flush();


            }
        } catch (IOException e) {
            System.out.println("Error Server");
        }
    }
}
