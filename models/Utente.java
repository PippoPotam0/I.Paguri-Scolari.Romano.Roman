package models;

public class Utente{

    private String nome;
    private double credito;
    private List<Prodotto> prodottiAcquistati;

        Utente(String nome, double credito) {
            this.nome = nome;
            this.credito = credito;
            this.prodottiAcquistati = new ArrayList<>();
        }

        void visualizzaDashboard() {
            System.out.println("Benvenuto " + nome);
            System.out.println("Credito: " + credito +"$");
            System.out.println("Inventario: ");

            for (Prodotto prodotto : prodottiAcquistati) {
                System.out.println(prodotto.nome);
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