package it.ramadani;

public class Messaggio {
    int id;
    String autore;
    String testo;
    
    public Messaggio(int id, String autore, String testo) {
        this.id = id;
        this.autore = autore;
        this.testo = testo;
    }

    @Override
    public String toString() {
        return "Messaggio [id= " + id + ", autore= " + autore + ", testo= " + testo + "]\n";
    }

}
