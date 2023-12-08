package models;

    public abstract class Item{

    private String nome;
    private int quantita;
    private double prezzo; 
    boolean acquistato;

    public Item(String nome, int quantita, double prezzo, boolean acquistato){
        this.nome = nome;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.acquistato = acquistato;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + 
        "Quantità: " + quantita + 
        "Prezzo: " + prezzo;
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

}