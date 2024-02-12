package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    @Override
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(Main.port);

            while (!Thread.interrupted()){
                Socket socketClient = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);

                String mesajPrimit = in.readLine();

                String[] split = mesajPrimit.split(" ");

                if (split[0].equals("Heartbeat")){
                    out.println("Alive");
                }

                in.close();
                out.close();
                socketClient.close();
            }
        } catch (IOException e) {
            System.out.println("eroare server thread");
            throw new RuntimeException(e);
        }
    }
}
