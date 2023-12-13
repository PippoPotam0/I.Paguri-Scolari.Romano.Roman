package models;

import java.util.List;

public class Negozio {
    private String nome;
    private List<Item> inventario;

    public Negozio(String nome, List<Item> inventario) {
        this.nome = nome;
        this.inventario = inventario;
    }

    // restituisce l'inventario del negozio
    public List<Item> getInventario() {
        return inventario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
