package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import models.Consumabili;
import models.Item;
import models.Negozio;
import models.Oggetti;
import models.Servizi;
import models.Utente;

public class UIManager {

    private static Scanner sc; // variabile per l'input da tastiera

    private final String TECH = "TECH";
    private final String TOOL = "TOOL";
    private final String FURNITURE = "FURNITURE";
    private final String BEVANDE = "BEVANDE";
    private final String CIBO = "CIBO";
    private static LocalDate data = LocalDate.now();
    static Timer timer = new Timer();
    private static final String MENU = """
        ------------------------------------------------------------

        \033[34mData:  \033[0m""" + data + """

                       
            \033[31m            ---MENU PRINCIPALE---\033[0m

            \033[32m1)\033[0m Visualizza Negozi      \033[33m2)\033[0m Visualizza Dashboard

            \033[35m3)\033[0m Visualizza menu        \033[36m4)\033[0m Ricarica denaro        
                       
                      0)Esci dall'applicazione

        ------------------------------------------------------------
            """;

    // mostra il menu dei negozi e torna la scelta dell'utente
    public static int menuNegozio(double credito) {
        System.out.println("""
        ------------------------------------------------------------

        \033[34mData:  \033[0m""" + data + """


            \033[34m                  ---MENU NEGOZI---\033[0m

            \033[32m        1)\033[0mBrico      \033[33m2)\033[0mEsselunga     \033[35m3)\033[0mLet's Relax
                               0)Torna indietro

                                Credito= """ + credito + """

        ------------------------------------------------------------
                """);
        return askInputInt();
    }

    public UIManager() {
        this.sc = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println(MENU); // stampa il menu principale
    }

    public static void divisore() {
        System.out.println("------------------------------------------------------------");
    }

    public static String askInput() {
        while (true) {
            System.out.print("-> ");
            try {
                return sc.nextLine();
            } catch (Exception e) {
                // Gestisci l'eccezione (puoi anche stampare un messaggio di errore)
                System.out.println("Input non valido, riprova.");
                sc.nextLine(); // Pulisci il buffer dell'input
            }
        }
    }

    public static int askInputInt() {
        while (true) {
            System.out.print("-> ");
            try {
                return sc.nextInt();
            } catch (Exception e) {
                // Gestisci l'eccezione (puoi anche stampare un messaggio di errore)
                System.out.println("Input non valido, riprova.");
                sc.nextLine(); // Pulisci il buffer dell'input
            }
        }
    }

    public static double askInputDouble() {
        while (true) {
            System.out.print("-> ");
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                // Gestisci l'eccezione (puoi anche stampare un messaggio di errore)
                System.out.println("Input non valido, riprova.");
                sc.nextLine(); // Pulisci il buffer dell'input
            }
        }
    }

    public static String menuCliente() {
        System.out.println("\nBenvenuto/a \nInserisci il tuo nome: ");
        return askInput();
        // chiede il nome all'utente all'apertura del programma
    }

    // mostra l'inventario di un negozio e consente all'utente di effettuare degli
    // acquisti
    public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi) {
        int i = 0;
        Negozio negozioCorrente = getNegozio(sceltaNegozio, listaNegozi);
        if (negozioCorrente != null) {
            boolean continuaAcquisti = true;
            do {
                System.out.println("Inventario di " + listaNegozi.get(sceltaNegozio-1).getNome());
                for (Item item : negozioCorrente.getInventario()) {
                    i++;
                    if (item instanceof Consumabili) {
                        Consumabili consumabile = (Consumabili) item;
                        String str = calcoloScadenza(consumabile.getScadenza(), data);
                        System.out.println("\n" + i + ") " + consumabile.toString() + str);
                    }else {
                        System.out.println("\n" + i + ") " + item.toString());
                    }
                }

                System.out.println("\nCredito disponibile: " + user.getCredito() + "$");
                System.out.println("\nSeleziona un oggetto da acquistare o 0 per tornare al menu principale: ");

                int sceltaOggetto = askInputInt();
                if (sceltaOggetto == 0) {
                    System.out.println(MENU);
                    continuaAcquisti = false;
                } else {
                    acquistaOggetto(user, negozioCorrente, sceltaOggetto);
                }
            } while (continuaAcquisti);
        } else {
            System.err.println("Negozio non trovato\n");
        }
    }

    // restituisce un oggetto Negozio a seconda del numero scelto nel Menu Negozi
    private static Negozio getNegozio(int numeroNegozio, List<Negozio> listaNegozi) {
        if (numeroNegozio >= 1 && numeroNegozio <= listaNegozi.size()) {
            return listaNegozi.get(numeroNegozio - 1);
        } else {
            System.err.println("Numero del negozio non valido\n");
            return null;
        }
    }

    // consente di acquistare un prodotto in un negozio
    private static void acquistaOggetto(Utente user, Negozio negozio, int sceltaOggetto) {
        if (sceltaOggetto >= 1 && sceltaOggetto <= negozio.getInventario().size()) {
            Item itemScelto = negozio.getInventario().get(sceltaOggetto - 1);

            if (itemScelto instanceof Item) {
                Item itemCopia = (Item) itemScelto.clone();

                if (user.getCredito() >= itemScelto.getPrezzo() && itemScelto.getQuantita() > 0) {
                    boolean controllo = false;
                    for (Item item : user.getProdottiAcquistati()) {
                        if (item.getNome().equals(itemScelto.getNome())) {
                            item.setQuantita(item.getQuantita() + 1);
                            controllo = true;
                            break;
                        }
                    }
                    if (!controllo) {
                        itemCopia.setQuantita(1);
                        user.aggiungiProdottoAcquistato(itemCopia);
                    }

                    user.setCredito(user.getCredito() - itemScelto.getPrezzo());
                    user.setCredito(Math.round(user.getCredito() * 100.00) / 100.00);
                    
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

        divisore();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Gestisci l'eccezione se necessario
            e.printStackTrace();
        }  
    }

    // metodo per il calcolo della scadenza di un alimento
    public static String calcoloScadenza(String scadenza, LocalDate data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
        LocalDate dataAttuale = LocalDate.now();

        if (dataAttuale.isAfter(dataScadenza)) {
            return " (SCADUTO)";
        }else {
            return "";
        }
    }

    public void aggiungiSoldi(Utente user){
        System.out.println("""
        Credito residuo: """ + user.getCredito() + """

        Inserisci il numero di soldi da aggiungere:""");
        double soldi = askInputDouble();
        user.setCredito(user.getCredito() + soldi);
        System.out.println("\033[1m\nHai aggiunto " + soldi + " al tuo credito\n\033[0m");
    }

    // metodo principale che avvia l'applicazione interfacciandosi anche con
    // l'utente
    public void runUi() {

        List<Negozio> listaNegozi = new ArrayList<>();

        Oggetti oggetto1 = new Oggetti("Telecamera di sicurezza", 9, 99.90, "1 anno", FURNITURE);
        Oggetti oggetto2 = new Oggetti("Metro", 25, 8.20, "1 mese", TOOL);
        Oggetti oggetto3 = new Oggetti("Trapano elettrico", 3, 45.00, "6 mesi", TECH);
        Oggetti oggetto4 = new Oggetti("Cacciavite", 12, 15.0, "1 mese", TOOL);

        Consumabili consumabile1 = new Consumabili("Actimel", 3, 2.00, "2024-12-02", BEVANDE);
        Consumabili consumabile2 = new Consumabili("Focaccina", 6, 7.00, "2023-12-08", CIBO);
        Oggetti oggetto5 = new Oggetti("Acido Muriatico(HCl)", 12, 15.0, "14 giorni", TOOL);
        Consumabili consumabile3 = new Consumabili("Succo d'ananas", 4, 5.00, "2024-01-21", BEVANDE);

        Servizi servizio1 = new Servizi("Massaggio corpo superiore", 3, 30.00, "30 minuti");
        Servizi servizio2 = new Servizi("Massaggio completo", 3, 55.00, "1 ora");
        Servizi servizio3 = new Servizi("Massaggio corpo inferiore", 1, 65.00, "1 ora");
        

        List<Item> inventarioBrico = new ArrayList<>();
        inventarioBrico.add(oggetto1);
        inventarioBrico.add(oggetto2);
        inventarioBrico.add(oggetto3);
        inventarioBrico.add(oggetto4);

        List<Item> inventarioEsselunga = new ArrayList<>();
        inventarioEsselunga.add(consumabile1);
        inventarioEsselunga.add(consumabile2);
        inventarioEsselunga.add(oggetto5);
        inventarioEsselunga.add(consumabile3);

        List<Item> inventarioLetsRelax = new ArrayList<>();
        inventarioLetsRelax.add(servizio1);
        inventarioLetsRelax.add(servizio2);
        inventarioLetsRelax.add(servizio3);

        Negozio brico = new Negozio("Brico", inventarioBrico);
        Negozio esselunga = new Negozio("Esselunga", inventarioEsselunga);
        Negozio letsRelax = new Negozio("LetsRelax", inventarioLetsRelax);

        listaNegozi.add(brico);
        listaNegozi.add(esselunga);
        listaNegozi.add(letsRelax);

        String nome = menuCliente();
        Utente user = new Utente(nome);

        String choice;

        this.printMenu();
        do {
            choice = askInput();
            switch (choice) {
                case "1":
                    int choice2 = menuNegozio(user.getCredito());
                    if (choice2 == 0) {
                        System.out.println("Torna indietro");
                        askInput();
                        printMenu();
                    } else {
                        if (choice2 >= 1 && choice2 <= listaNegozi.size()) {
                            negozio(user, choice2, listaNegozi);
                        } else {
                            System.err.println("Scelta negozio non valida, riprova!\n");
                        }
                    }
                    break;

                case "2":
                    user.visualizzaDashboard();
                    divisore();
                    break;

                case "3":
                    this.printMenu();
                    break;

                case "4":
                    aggiungiSoldi(user);
                    break;

                case "0":
                    System.out.println("\nApplicazione chiusa, grazie e arrivederci!\n");
                    System.exit(0);
                    break;

            }
        } while (choice.equalsIgnoreCase("0") == false);
    }
}
