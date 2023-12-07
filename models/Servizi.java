package models;

public class Servizi extends Item {
    private String tempoPrestazione;

    public Servizi(String nome, int quantita, double prezzo, boolean acquistato, String tempoPrestazione) {
        super(nome, quantita, prezzo, acquistato);
        this.tempoPrestazione = tempoPrestazione;
    }

    public String getTempoPrestazione() {
        return tempoPrestazione;
    }
    
    public void setTempoPrestazione(String tempoPrestazione) {
        this.tempoPrestazione = tempoPrestazione;
    }

    @Override
    public String toString() {
        return super.toString() + "Tempo di prestazione: " + tempoPrestazione;
    }
}
