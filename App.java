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


//Negozio = array di Item 
//Piu negozi = piu array di Item
//Nome del negozio = nome dell'array
//Item = classe astratta
//Servizi, Oggetti, Consumabili = classi di Item con aggiuntivi attributi
//Utente = oggetto;

import java.util.ArrayList;
import java.util.List;
import models.Negozio;
import ui.UIManager;
import models.Oggetti; 
import models.Item;

public class App extends UIManager {
          private static List<Negozio> listaNegozi = new ArrayList<>();
    public static void main(String[] args) {


        Oggetti oggetto1 = new Oggetti("Oggetto1", 10, 20.0, false, "Garanzia1", "Categoria1");
        Oggetti oggetto2 = new Oggetti("Oggetto2", 5, 15.0, false, "Garanzia2", "Categoria2");
        Oggetti oggetto3 = new Oggetti("Oggetto3", 8, 25.0, false, "Garanzia3", "Categoria3");

        List<Item> inventarioNegozio1 = new ArrayList<>();
        inventarioNegozio1.add(oggetto1);
        inventarioNegozio1.add(oggetto2);

        // Creare un inventario per il secondo negozio
        List<Item> inventarioNegozio2 = new ArrayList<>();
        inventarioNegozio2.add(oggetto3);

        // Creare le istanze dei negozi passando gli inventari
        Negozio negozio1 = new Negozio(inventarioNegozio1);
        Negozio negozio2 = new Negozio(inventarioNegozio2);

        // Aggiungere i negozi alla lista
        listaNegozi.add(negozio1);
        listaNegozi.add(negozio2);


        UIManager uiManager = new UIManager();

		uiManager.run();

    }
}
