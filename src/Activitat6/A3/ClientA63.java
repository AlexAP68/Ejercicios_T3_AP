package Activitat6.A3;

import Activitat3.ClientA3;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientA63 {
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

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe una calle");
        String calle = sc.nextLine();
        System.out.println("Escribe codiPostal");
        String cp = sc.nextLine();
        System.out.println("Escribe Pais");
        String pais = sc.nextLine();
        System.out.println("Escribe NombreCasa.");
        String casa = sc.nextLine();

        JSONObject json = new JSONObject();
        json.put("calle", calle);
        json.put("cp", cp);
        json.put("pais", pais);
        json.put("casa", casa);
        System.out.println(json);

        try {
            socket.connect(direccion);
            while (true) {

                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.print(json + "\n");
                pw.flush();
                BufferedReader bfr = ClientA63.getFlujo(socket.getInputStream());
                System.out.println("El resultado fue: " + bfr.readLine());
                sc.nextLine();
//socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error Client");
        }
    }
}