package models;

public class Consumabili extends Item {
    
    private String scadenza;
    private String categoria;

    public Consumabili(String nome, int quantita, double prezzo, String scadenza, String categoria) { 
        super(nome, quantita, prezzo);                                                                          
        this.scadenza = scadenza;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + " - Scadenza: " + scadenza + " - Categoria: " + categoria;
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
