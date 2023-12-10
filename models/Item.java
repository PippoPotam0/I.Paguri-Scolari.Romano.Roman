package models;

    public abstract class Item implements Cloneable{

    private String nome;
    private int quantita;
    private double prezzo; 

    public Item(String nome, int quantita, double prezzo){
        this.nome = nome;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public Item(Item item){
        this.nome = item.getNome();
        this.quantita = item.getQuantita();
        this.prezzo = item.getPrezzo();
    }

    @Override
    public String toString(){
        return "Nome: " + nome + " - Quantità: " + quantita + " - Prezzo: " + prezzo + "$";
    }

    public String getNome(){  
        return  nome; 
    } 

    public int getQuantita(){
        return quantita;
    }   

    public double getPrezzo(){
        return prezzo;
    }       

    public void setNome(String nome){
        this.nome = nome;
    }       

    public void setQuantita(int quantita){
        this.quantita = quantita;
    }       

    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }      

    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}