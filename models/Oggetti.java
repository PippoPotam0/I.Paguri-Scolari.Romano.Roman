package models;

public class Oggetti extends Item {
    private String garanzia;
    private String categoria;

    public Oggetti(String nome, int quantita, double prezzo, String garanzia, String categoria) {
        super(nome, quantita, prezzo);                                                                   
        this.garanzia = garanzia;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + " - Garanzia: " + garanzia + " - Categoria: " + categoria;
    }

    public String getGaranzia() {
        return garanzia;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setGaranzia(String garanzia) {
        this.garanzia = garanzia;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
