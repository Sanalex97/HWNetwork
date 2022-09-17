package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        String host = getIPHost("netology.homework");
        int port = 8880;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {

            while (!clientSocket.isClosed()) {
                if (in.ready()) {
                    String mes = in.readLine();
                    System.out.println(mes);

                    if (mes.equals("Write your name")) {
                        out.println("Alex");
                    } else if (mes.equals("Are you child? (yes/no)")) {
                        out.println("yes");
                    } else {
                        clientSocket.close();
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIPHost(String host) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(host);
        return inetAddress.getHostAddress();
    }
}
