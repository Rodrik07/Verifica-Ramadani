package it.ramadani;

import java.util.ArrayList;

public class Lavagna {
    ArrayList<Messaggio> messagio = new ArrayList<>();
    int prossimoID = 1;

    //aggiutna nuovo messaggio
    public Messaggio aggiungi(String autore, String testo){
        Messaggio m = new Messaggio(prossimoID++, autore, testo);    
        messagio.add(m);
        return m;
    }

    //restituzione della lista di messaggi
    public String lista(){
        String testo = "BOARD:\n";
        for(int i = 0; i < messagio.size(); i++){
            testo += messagio.get(i).toString();
        }
        testo += "\nEND";
        return testo;
    }

    //rimozione messaggio 
    public String elimina(int id, String autore){
        for(int i = 0; i < messagio.size(); i++){
            Messaggio m = messagio.get(i);
            if (m.id == id) {
                messagio.remove(i);
                return "OK DELETED";
            }
        }
        return "ID NOT FOUND";
    }

}
