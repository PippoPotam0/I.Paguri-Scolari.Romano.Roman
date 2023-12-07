package models;

public class Oggetti extends Prodotto {
    public String garanzia;
    public String categoria;

    public Oggetti(String nome, int quantita, double prezzo, String garanzia, String categoria) {
        super(nome, quantita, prezzo);
        this.garanzia = garanzia;
        this.categoria = categoria;
    }
}
