package Activitat2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorA2 {
    static boolean exit = false;
    public static void main(String[] args) {
        int puertoDestino = 2222;
        //Coje un numero aleatorio de uno al 100
        int random = aleatorio();
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

                int numero = Integer.parseInt(linea);
                OutputStream os = server.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
//Write In Stream

            //Segun el numero que reciba sera una opcion u otra

                if (numero < random){

                    pw.write("El numero es mayor" + "\n");
                    pw.flush();
                } else if (numero > random){
                    pw.write("El numero es menor" + "\n");
                    pw.flush();
                }
                else {
                    pw.write("El numero es correcto" + "\n");
                    pw.flush();
                    exit = true;
                }

                //Por el servidor  pasa el resultado
                //System.out.println(random);


            }
        } catch (IOException e) {
            System.out.println("Error Server");
        }
    }

    public static int  aleatorio(){
        return (int) (Math.random() * 100);
    }
}
