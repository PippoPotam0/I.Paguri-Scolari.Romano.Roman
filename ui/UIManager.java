package ui;

import java.util.Scanner;
import java.util.List;
import models.*;

public class UIManager {

    private static Scanner sc;

    private final String MENU = """
            
                        ---Menu principale---

            1)Visualizza Negozi        2)Visualizza Dashboard

            3)Visualizza menu          0)Esci dall'applicazione
            """;

    public UIManager() {
        this.sc = new Scanner(System.in);
    }

    private List<Negozio> listaNegozi; 

    public UIManager(List<Negozio> listaNegozi) {
        this.sc = new Scanner(System.in);
        this.listaNegozi = listaNegozi; 
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
        System.out.println("\nBenvenuto/a \nInserisci il tuo nome: ");
        return sc.nextLine();
    }

    public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi) {
        Negozio negozioCorrente = getNegozio(sceltaNegozio, listaNegozi);
        if (negozioCorrente != null) {
            boolean continuaAcquisti = true;
            do {
                System.out.println("Inventario del Negozio " + sceltaNegozio);
    
                List<Item> inventarioNegozio = negozioCorrente.getInventario();
                for (int i = 0; i < inventarioNegozio.size(); i++) {
                    Item item = inventarioNegozio.get(i);
                    System.out.println((i + 1) + ") " + item.getNome() + " - Prezzo: " + item.getPrezzo());
                }
    
                System.out.println("Credito disponibile: " + user.getCredito());
                System.out.println("Seleziona un oggetto da acquistare o 0 per tornare al menu principale:");
    
                int sceltaOggetto = sc.nextInt();
                if (sceltaOggetto == 0) {
                    continuaAcquisti = false;
                } else {
                    acquistaOggetto(user, negozioCorrente, sceltaOggetto);
                }
            } while (continuaAcquisti);
        } else {
            System.err.println("Negozio non trovato");
        }
    }

    private static Negozio getNegozio(int numeroNegozio, List<Negozio> listaNegozi) {
        if (numeroNegozio >= 1 && numeroNegozio <= listaNegozi.size()) {
            return listaNegozi.get(numeroNegozio - 1); 
        } else {
            System.err.println("Numero del negozio non valido");
            return null;
        }
    }

    private static void acquistaOggetto(Utente user, Negozio negozio, int sceltaOggetto) {
        if (sceltaOggetto >= 1 && sceltaOggetto <= negozio.getInventario().size()) {
            Item itemScelto = negozio.getInventario().get(sceltaOggetto - 1);
    
            if (itemScelto instanceof Oggetti) {
                Oggetti oggettoScelto = (Oggetti) itemScelto;
    
                if (user.getCredito() >= oggettoScelto.getPrezzo() && oggettoScelto.getQuantita() > 0) {
                    user.aggiungiProdottoAcquistato(oggettoScelto);
                    user.setCredito(user.getCredito() - oggettoScelto.getPrezzo());
                    oggettoScelto.setQuantita(oggettoScelto.getQuantita() - 1);
                    System.out.println("Hai acquistato: " + oggettoScelto.getNome());
                } else {
                    System.err.println("Denaro insufficiente o oggetto non disponibile");
                }
            } else {
                System.err.println("Tipo di oggetto non gestito");
            }
        } else {
            System.err.println("Scelta oggetto non valida, riprova!");
        }
    }

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
                    int choice2 = menuNegozio(user.getCredito());
                        if (choice2 >= 1 && choice2 <= 3) {
                            negozio(user, choice2, listaNegozi);
                        } else {
                            System.err.println("Scelta negozio non valida, riprova!");
                        }
                    break;

                case "2":
                    user.visualizzaDashboard();
                    break;

                case "3":
                    this.printMenu();
                    break;
                
                case "0":
                    System.out.println("\nApplicazione chiusa, grazie e arrivederci!\n");
                    System.exit(0);
                    break;
            
                default:
                    System.err.println("Opzione sbagliata: digita 3 per rivedere il menu");
                    break;
            }
        }while(choice.equalsIgnoreCase("0") == false);
    }


    public static int menuNegozio(double credito) {
        System.out.println("""
                           ---MENU---
        1)Negozio n.1     2)Negozio n.2     3)Negozio n.3     

                        Credito =  """ + credito + """
                """);
        return sc.nextInt();
    }

}