package models;

public class Servizi extends Prodotto {
    public String tempoPrestazione;

    public Servizi(String nome, int quantita, double prezzo, String tempoPrestazione) {
        super(nome, quantita, prezzo);
        this.tempoPrestazione = tempoPrestazione;
    }
}
