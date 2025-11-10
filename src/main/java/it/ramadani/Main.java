package it.ramadani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    final static int PORTA = 12345;
    static Lavagna lavagna = new Lavagna();
    
        public static void main(String[] args) throws IOException {
            System.out.println("Server lavagna avviato la porta e': " + PORTA);
            try(ServerSocket serverSocket = new ServerSocket(PORTA)) {
                //il server rimane in attesa fino all'arrivo del client
                while (true) {
                    Socket socket = serverSocket.accept(); 
                    gestisciClient(socket);
                }
            } catch (IOException e) {

            }
        }

        private static void gestisciClient(Socket socket) throws IOException{
            String utente = null;

            try (
               //input e output per la comunicazione con il client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
            ) {
                out.println("WELCOME");

                String linea;
                while((linea = in.readLine()) != null){
                    linea = linea.trim(); //serve solamente a romuovere gli spazi che non servono
                    if(linea.isEmpty()){
                        continue; //ingora le righe che sono vuote
                    }
                    //Divisione di una stringa in comando e argomento
                    String[] parti = linea.split(" ", 2);
                    String cmd = parti[0].toUpperCase();

                    if (utente == null && !cmd.equals("LOGIN") && !cmd.equals("QUIT")) {
                        out.println("ERR LOGINREQUIRED");
                        continue;
                    }
                    switch (cmd) {
                        case "LOGIN":
                            if (parti.length < 2 || parti[1].trim().isEmpty()) {
                                out.println("ERR SYNTAX");
                            } else{
                                utente = parti[1].trim();
                                out.println("OK WELCOME");
                            }
                            break;
                        case "ADD": 
                        if (parti.length < 2 || parti[1].trim().isEmpty()) {
                            out.println("ERR SYNTAX");
                        } else{
                            Messaggio m = lavagna.aggiungi(utente, parti[1].trim());
                            out.println("OK ADDED" + m.id);
                        }    
                        case "LIST":
                            out.println(lavagna.lista());
                            break;
                        case "DELL":
                            if (parti.length < 2 || parti[1].trim().isEmpty()) {
                                out.println("ERR SYNTAX");
                            } else{
                                try {
                                    int id = Integer.parseInt(parti[1]);
                                    out.println(lavagna.elimina(id, utente));
                                } catch (NumberFormatException e) {
                                    out.println("ERR SYNTAX");
                                }
                            } 
                            break;
                        case "QUIT": 
                            out.println("BYE");
                            socket.close();
                            break;
                        default:
                            out.println("ERR UNKNOWNCMD");
                            break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Errore CLient: " + e.getMessage());
            }
        }


    
}