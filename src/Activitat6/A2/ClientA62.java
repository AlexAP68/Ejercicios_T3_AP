package Activitat6.A2;

import Activitat2.ClientA2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientA62 {
    public static BufferedReader getFlujo(InputStream is) {
        InputStreamReader isr
                = new InputStreamReader(is);
        BufferedReader bfr
                = new BufferedReader(isr);
        return bfr;
    }
    public static void main(String[] args) {
        String destino = "localhost";
        int puertoDestino = 2222;
        Socket socket = new Socket();
        InetSocketAddress direccion = new InetSocketAddress(

                destino, puertoDestino);
        try {
            socket.connect(direccion);
            while (true) {
                int numero = 0;
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(System.in));

                try {
                    System.out.println("Elige un numero entre 0 y 100:");
                    numero = getValidNumber(reader, 0, 100);
                } catch (IOException e) {
                    System.out.println("Error al leer la entrada.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingresa un número válido.");
                }
                String txt = String.valueOf(numero);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.print(txt + "\n");
                pw.flush();
                BufferedReader bfr = ClientA62.getFlujo(socket.getInputStream());
                System.out.println(bfr.readLine());
//socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error Client");
        }


    }
    private static int getValidNumber(BufferedReader reader, int min, int max) throws IOException {
        while (true) {
            String txt = reader.readLine();
            int num;
            try {
                num = Integer.parseInt(txt);
                if (num >= min && num <= max) {
                    return num;
                } else {
                    System.out.println("Error: Ingresa un número entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }
}