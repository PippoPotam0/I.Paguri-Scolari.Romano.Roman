package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.*;
import java.time.LocalDate;

public class UIManager {

    private static Scanner sc;

    private final String TECH = "TECH";
    private final String TOOL = "TOOL";
    private final String FURNITURE = "FURNITURE";
    private final String BEVANDE = "BEVANDE";
    private final String CIBO = "CIBO"; 
    private LocalDate data = LocalDate.now();
    private final String MENU = """

            Data:""" + data + """
                        
                        ---Menu principale---

            1)Visualizza Negozi        2)Visualizza Dashboard

            3)Visualizza menu          0)Esci dall'applicazione
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
                    System.out.println((i + 1) + ") " + item.getNome() + " - Prezzo: " + item.getPrezzo() + " - Quantita': " + item.getQuantita());
                }
    
                System.out.println("Credito disponibile: " + user.getCredito());
                System.out.println("Seleziona un oggetto da acquistare o 0 per tornare al menu principale: ");
    
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

        
        
        List<Negozio> listaNegozi = new ArrayList<>(); 

        Oggetti oggetto1 = new Oggetti("Oggetto1", 10, 20.0, false, "Garanzia1", TECH);
        Oggetti oggetto2 = new Oggetti("Oggetto2", 5, 15.0, false, "Garanzia2", TOOL);
        Consumabili consumabile1 = new Consumabili("Coca-Cola", 3, 11, false, "27/12/2024", BEVANDE);
        Servizi servizio1 = new Servizi("Massaggio", 3, 48, false, "30 minuti");

        List<Item> inventarioNegozio1 = new ArrayList<>();
        inventarioNegozio1.add(oggetto1);
        inventarioNegozio1.add(oggetto2);

        List<Item> inventarioNegozio2 = new ArrayList<>();
        inventarioNegozio2.add(consumabile1);

        List<Item> inventarioNegozio3 = new ArrayList<>();
        inventarioNegozio3.add(servizio1);

        Negozio negozio1 = new Negozio(inventarioNegozio1);
        Negozio negozio2 = new Negozio(inventarioNegozio2);
        Negozio negozio3 = new Negozio(inventarioNegozio3);

        listaNegozi.add(negozio1);
        listaNegozi.add(negozio2);
        listaNegozi.add(negozio3);


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
                        /* 
                        switch(choice2) {
                            case 1:
                                negozio1.getInventario();
                                break;
                            default:
                            
                                break;
                        }*/
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
            
        Data:""" + data + """
                        
                           ---MENU---
        1)Negozio n.1     2)Negozio n.2     3)Negozio n.3     
                        0)Torna indietro

                        Credito =  """ + credito + """
                """);
        return sc.nextInt();
    }

    public static void aggiungiSoldi(Utente user){
        System.out.println("Inserisci i soldi da aggiungere: ");

    }

}