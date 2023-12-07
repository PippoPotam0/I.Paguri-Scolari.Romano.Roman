/*Progettare un'applicazione che gestisce l'inventario di un negozio ed i suoi clienti. 
Un utente che apre l'applicazione può accedere come cliente inserendo un nome e poi visualizzare una lista di negozi. 
Ogni negozio ha un inventario di Item. Ogni Item ha un nome, una quantità ed un prezzo. 
Gli item si dividono in: Servizi (hanno anche un tempo di prestazione) 
Oggetti (hanno un tempo di garanzia ed una categoria (valore che ha dominio TECH, TOOL, FURNITURE) e 
Consumabili (hanno una data di scadenza ed una categoria (valore con dominio BEVANDE, CIBO).
L'utente ha un credito iniziale di 200$ e ogni volta che decide di comprare un Item, 
paga la somma necessaria a compare 1x Item selezionato. 
Ricapitolando, l'utente accede ad un negozio, vede i suoi item, sceglie se comprare un Item o tornare indietro, 
e nella dashboard iniziale vede il suo credito e il suo inventario (le cose che ha comprato). */

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