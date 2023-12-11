package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private final String BEVANDE = "BEVANDE";
    private final String CIBO = "CIBO";
    private static LocalDate data = LocalDate.now();
    private static final String MENU = """

            Data:""" + data + """

                        ---MENU PRINCIPALE---

            1)Visualizza Negozi        2)Visualizza Dashboard

            3)Visualizza menu          0)Esci dall'applicazione
            """;

    public UIManager() {
        this.sc = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println(MENU); // stampa il menu principale
    }

    public static String askInput() {
        System.out.print("-> "); // metodo che chiede un input di testo all'utente
        return sc.nextLine();
    }

    public static int askInputInt() {
        System.out.print("-> "); // metodo che chiede un input di un n intero all'utente
        return sc.nextInt();
    }

    public static String menuCliente() {
        System.out.println("\nBenvenuto/a \nInserisci il tuo nome: ");
        return askInput();
        // chiede il nome all'utente all'apertura del programma
    }

    // mostra l'inventario di un negozio e consente all'utente di effettuare degli
    // acquisti
    public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi) {
        Negozio negozioCorrente = getNegozio(sceltaNegozio, listaNegozi);
        if (negozioCorrente != null) {
            boolean continuaAcquisti = true;
            do {
                System.out.println("Inventario del Negozio " + sceltaNegozio);

                for (Item item : negozioCorrente.getInventario()) {
                    System.out.println(item.toString());
                }

                System.out.println("\nCredito disponibile: " + user.getCredito());
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

    public void calcoloScadenza(String scadenza, LocalDate data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
        LocalDate dataAttuale = LocalDate.now();

        if (dataAttuale.isBefore(dataScadenza)) {
            System.out.println("L'alimento è ancora valido.");
        } else {
            System.out.println("L'alimento è scaduto.");
        }
    }

    // metodo principale che avvia l'applicazione interfacciandosi anche con
    // l'utente
    public void run() {

        List<Negozio> listaNegozi = new ArrayList<>();

        Oggetti oggetto1 = new Oggetti("Telecamera di sicurezza", 9, 99.90, "1 anno", TECH);
        Oggetti oggetto2 = new Oggetti("Metro", 25, 9.20, "1 mese", TOOL);
        Oggetti oggetto3 = new Oggetti("Trapano elettrico", 3, 45.0, "6 mesi", TECH);
        Oggetti oggetto4 = new Oggetti("Cacciavite", 12, 15.0, "1 mese", TOOL);
        Consumabili consumabile1 = new Consumabili("Coca-Cola", 3, 2, "27/2/2024", BEVANDE);
        Consumabili consumabile2 = new Consumabili("Panino", 6, 7, "08/12/2023", CIBO);
        Servizi servizio1 = new Servizi("Massaggio", 3, 30, "30 minuti");
        Servizi servizio2 = new Servizi("Massaggio", 3, 55, "1 ora");

        List<Item> inventarioBrico = new ArrayList<>();
        inventarioBrico.add(oggetto1);
        inventarioBrico.add(oggetto2);
        inventarioBrico.add(oggetto3);
        inventarioBrico.add(oggetto4);

        List<Item> inventarioEsselunga = new ArrayList<>();
        inventarioEsselunga.add(consumabile1);
        inventarioEsselunga.add(consumabile2);

        List<Item> inventarioMassaggi = new ArrayList<>();
        inventarioMassaggi.add(servizio1);
        inventarioMassaggi.add(servizio2);

        Negozio brico = new Negozio(inventarioBrico);
        Negozio esselunga = new Negozio(inventarioEsselunga);
        Negozio centroMassaggi = new Negozio(inventarioMassaggi);

        listaNegozi.add(brico);
        listaNegozi.add(esselunga);
        listaNegozi.add(centroMassaggi);

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
                    break;

                case "3":
                    this.printMenu();
                    break;

                case "0":
                    System.out.println("\nApplicazione chiusa, grazie e arrivederci!\n");
                    System.exit(0);
                    break;

            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

    // mostra il menu dei negozi e torna la scelta dell'utente
    public static int menuNegozio(double credito) {
        System.out.println("""

                Data:""" + data + """

                             ---MENU NEGOZI---
                1)Brico     2)Esselunga     3)Centro massaggi
                              0)Torna indietro

                                Credito= """ + credito + """
                """);
        return askInputInt();
    }

}
