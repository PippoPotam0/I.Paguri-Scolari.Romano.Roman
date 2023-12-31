package models;

import java.util.ArrayList;
import java.util.List;

public class Utente{

    private String nome;
    private double credito;
    private List<Item> prodottiAcquistati; 

    public Utente(String nome) {
        this.nome = nome;
        this.credito = 200.00;
        this.prodottiAcquistati = new ArrayList<>();
    }

    // stampa una dashboard con le informazioni sull'utente e i prodotti acquistati
    public void visualizzaDashboard() {
        System.out.println("Benvenuto, " + this.nome);
        System.out.println("Credito: " + this.credito +"$");
        System.out.println("Inventario: ");

        for (Item prodotto : prodottiAcquistati) {
            System.out.println(prodotto.toString());
        }
    }

    // aggiunge un prodotto acquistato alla lista dell'utente
    public void aggiungiProdottoAcquistato(Item item) {
        prodottiAcquistati.add(item);
    }

    public List<Item> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public String getNome() {
        return nome;
    }

    public double getCredito() {
        return credito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }   

}
