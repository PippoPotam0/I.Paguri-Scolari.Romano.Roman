


public abstract class Prodotto{

    private String nome;
    private int quantita;
    private double prezzo; 

    public Prodotto(String nome, int quantita, double prezzo){
        this.nome = nome;
        this.quantita = quantita;
        prezzo = this.prezzo;
    }

    public String toString(){
        return "Nome: " + nome + "Quantità: " + quantita + "Prezzo: " + prezzo;
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