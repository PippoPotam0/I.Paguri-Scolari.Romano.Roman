package models;

import java.util.List;

public class Negozio {
    private List<Item> inventario;

    public Negozio(List<Item> inventario) {
        this.inventario = inventario;
    }

    public List<Item> getInventario() {
        return inventario;
    }
}

