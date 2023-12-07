package models;

public class Consumabili extends Prodotto {
    
    private String scadenza;
    private String categoria;

    public Consumabili(String nome, String descrizione, String prezzo, String scadenza, String categoria) {
        super(nome, descrizione, prezzo);
        this.scadenza = scadenza;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  "Scadenza: " + scadenza + "Categoria: " + categoria;
    }

    public String getScadenza() {
        return scadenza;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    

}
