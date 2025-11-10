package it.ramadani;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Server avviato. In attesa di una connessione");

        ArrayList<Messaggio> messaggio = new ArrayList<>();
        int prossimoID = 1;

        
        serverSocket.close();
    }
}