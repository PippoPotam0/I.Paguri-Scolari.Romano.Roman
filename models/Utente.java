package models;

import java.util.ArrayList;
import java.util.List;

public class Utente{

    private String nome;
    private double credito;
    private List<Item> prodottiAcquistati;

        Utente(String nome, double credito) {
            this.nome = nome;
            this.credito = credito;
            this.prodottiAcquistati = new ArrayList<>();
        }

        void visualizzaDashboard() {
            System.out.println("Benvenuto " + nome);
            System.out.println("Credito: " + credito +"$");
            System.out.println("Inventario: ");

            for (Item prodotto : prodottiAcquistati) {
                System.out.println(prodotto.getNome());
            }
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