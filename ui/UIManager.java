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

package ui;
import java.util.Scanner;
import models.*;

public class UIManager {

     
    private static Scanner sc;

    private final String MENU = """
            
                        ---Menu principale---

            1)Visualizza Negozi        2)Visualizza Dashboard

            3)Visualizza menu

                        0)Esci dal programma
            """;

    public UIManager() {
        this.sc = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println(MENU);
    }

    public String askInput() {
        System.out.print("-> ");
        return sc.nextLine();
    }

    public int askInputInt() {
        System.out.print("-> ");
        return sc.nextInt();
    }
    
    public static String menuCliente() {
        System.out.println("Inserisci il tuo nome: ");
        return sc.nextLine();

    }

    //List<Item> negozio = new ArrayList<Item>(); Andrebbe teoricamente nella prima opzione del menu insieme a tutto quello che resta da finire

    public void run() {

        String nome = menuCliente();
        Utente user = new Utente(nome);

        int credito = 200;
        String choice;

        this.printMenu();
        do{
            choice = this.askInput();
            switch (choice) {
                case "1":
                    int choice2;
                    choice2 = menuNegozio(credito);
                    break;

                case "2":
                    user.visualizzaDashboard();
                    break;

                case "3":
                    this.printMenu();
                    break;
                
                case "0":
                    System.exit(0);
                    break;
            
                default:
                    System.err.println("Opzione sbagliata: digita 3 per rivedere il menu");
                    break;
            }
        }while(choice.equalsIgnoreCase("0") == false);
    }


    public static int menuNegozio(int credito) {
        System.out.println("""
                           ---MENU---
        1)Negozio n.1     2)Negozio n.2     3)Negozio n.3     

                        Credito =  """ + credito + """
                """);
        return sc.nextInt();
    }

    public static int aggiungiCredito(int credito) {
        return sc.nextInt() + credito;
    }

    public static int compra(int credito) {
        return credito;         //Fa finito sottraendo i soldi dal credito dello user
    }


}