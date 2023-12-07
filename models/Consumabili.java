package models;

public class Consumabili extends Item {
    
    private String scadenza;
    private String categoria;

    public Consumabili(String nome, int quantita, double prezzo, boolean acquistato, String scadenza, String categoria) { //categoria = BEVANDE, CIBO. vanno messi
        super(nome, quantita, prezzo, acquistato);                                                                           //come unici possibili valori
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
