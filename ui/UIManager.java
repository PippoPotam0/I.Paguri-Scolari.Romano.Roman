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
    private static LocalDate data = LocalDate.now();
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

    public static String askInput() {
        System.out.print("-> ");
        return sc.nextLine();
    }

    public static int askInputInt() {
        System.out.print("-> ");
        return sc.nextInt();
    }
    
    public static String menuCliente() {
        System.out.println("\nBenvenuto/a \nInserisci il tuo nome: ");
        return askInput();
    }
     
    public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi) {
        Negozio negozioCorrente = getNegozio(sceltaNegozio, listaNegozi);
        if (negozioCorrente != null) {
            boolean continuaAcquisti = true;
            do {
                System.out.println("Inventario del Negozio " + sceltaNegozio);
    
                List<Item> inventarioNegozio = negozioCorrente.getInventario();

                for(Item item : negozioCorrente.getInventario()) {
                    System.out.println(item.toString());
                }
    
                System.out.println("Credito disponibile: " + user.getCredito());
                System.out.println("Seleziona un oggetto da acquistare o 0 per tornare al menu principale: ");
    
                int sceltaOggetto = askInputInt();
                if (sceltaOggetto == 0) {
                    continuaAcquisti = false;
                } else {
                    acquistaOggetto(user, negozioCorrente, sceltaOggetto);
                }
            } while (continuaAcquisti);
        } else {
            System.err.println("Negozio non trovato\n");
        }
    }
    
    private static Negozio getNegozio(int numeroNegozio, List<Negozio> listaNegozi) {
        if (numeroNegozio >= 1 && numeroNegozio <= listaNegozi.size()) {
            return listaNegozi.get(numeroNegozio - 1); 
        } else {
            System.err.println("Numero del negozio non valido\n");
            return null;
        }
    }
    
    private static void acquistaOggetto(Utente user, Negozio negozio, int sceltaOggetto) {
        if (sceltaOggetto >= 1 && sceltaOggetto <= negozio.getInventario().size()) {
            Item itemScelto = negozio.getInventario().get(sceltaOggetto - 1); 

            if (itemScelto instanceof Item) {
                Item itemCopia = (Item) itemScelto.clone();

                if (user.getCredito() >= itemScelto.getPrezzo() && itemScelto.getQuantita() > 0) {
                    boolean controllo = false;
                    for(Item item : user.getProdottiAcquistati()){
                        if(item.getNome().equals(itemScelto.getNome())){
                            item.setQuantita(item.getQuantita() + 1);
                            controllo = true;
                            break;
                        }
                    }

                    if(!controllo) {
                        itemCopia.setQuantita(1);
                        user.aggiungiProdottoAcquistato(itemCopia);
                    }

                    user.setCredito(user.getCredito() - itemScelto.getPrezzo());
                    itemScelto.setQuantita(itemScelto.getQuantita() - 1);
                    System.out.println("\033[1m\nHai acquistato: " + itemScelto.getNome() + "\n\033[0m");
                } else {
                    System.err.println("\033[1m\nDenaro insufficiente o oggetto non disponibile\n\033[0m");
                }
            } else {
                System.err.println("\033[1m\nTipo non trattato\n\033[0m");
            }
        } else {
            System.err.println("\033[1m\nScelta oggetto non valida, riprova!\n\033[0m");
        }
    }

    
   


    public void run() {

        
        
        List<Negozio> listaNegozi = new ArrayList<>(); 

        Oggetti oggetto1 = new Oggetti("Oggetto1", 10, 20.0, "Garanzia1", TECH);
        Oggetti oggetto2 = new Oggetti("Oggetto2", 5, 15.0, "Garanzia2", TOOL);
        Consumabili consumabile1 = new Consumabili("Coca-Cola", 3, 2, "27/2/2024", BEVANDE);
        Consumabili consumabile2 = new Consumabili("Panino", 6, 7, "08/12/2023", BEVANDE);
        Servizi servizio1 = new Servizi("Massaggio", 3, 48, "30 minuti");

        List<Item> inventarioBrico = new ArrayList<>();
        inventarioBrico.add(oggetto1);
        inventarioBrico.add(oggetto2);

        List<Item> inventarioEsselunga = new ArrayList<>();
        inventarioEsselunga.add(consumabile1);

        List<Item> inventarioMassaggi = new ArrayList<>();
        inventarioMassaggi.add(servizio1);

        Negozio brico = new Negozio(inventarioBrico);
        Negozio esselunga = new Negozio(inventarioEsselunga);
        Negozio centroMassaggi = new Negozio(inventarioMassaggi);

        listaNegozi.add(brico);
        listaNegozi.add(esselunga);
        listaNegozi.add(centroMassaggi);


        String nome = menuCliente();
        Utente user = new Utente(nome);

        int credito = 200;
        String choice;

        this.printMenu();
        do{
            choice = askInput();
            switch (choice) {
                case "1":
                    int choice2 = menuNegozio(user.getCredito());
                        if(choice2 == 0) {
                            System.out.println("Torna indietro");
                            askInput();
                            printMenu();
                            }else{
                            if (choice2 >= 1 && choice2 <= listaNegozi.size()) {
                                negozio(user, choice2, listaNegozi);
                            }else {
                                System.err.println("Scelta negozio non valida, riprova!\n");
                            }
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
                    System.err.println("\n\nOpzione sbagliata: digita 3 per rivedere il menu");
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

                        Credito= """ + credito + """
                """);
        return askInputInt();
    }

    public static void aggiungiSoldi(Utente user){
        System.out.println("Inserisci i soldi da aggiungere: ");
        user.setCredito(user.getCredito() + askInputInt());
    }

}