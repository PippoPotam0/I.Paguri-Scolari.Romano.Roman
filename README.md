# **Shop Inventory and Customer Management Application**

### **Authors:**
- Scolari Filippo
- Romano Davide
- Roman Vlad

## Introduction
This application is designed to manage the inventory of a store and its customers. A user opening the application can access it as a customer by entering a name and then view a list of stores. Each store has an inventory of items. Each item has a name, quantity, and price. Items are categorized into:

- ***Services*** (also have a performance time)
- ***Objects*** (have a warranty time and a category with the domain values: TECH, TOOL, FURNITURE)
- ***Consumables*** (have an expiration date and a category with the domain values: BEVERAGES, FOOD)

The user has an initial credit of ***$200***, and each time they decide to buy an item, they pay the necessary amount to purchase 1x the selected item. To summarize, the user accesses a store, views its items, decides whether to buy an item or go back, and on the initial dashboard, sees their credit and inventory (the items they have bought).

## Features

- **User Authentication**: Users can log in with a name.
- **Store Navigation**: Users can browse a list of stores.
- **Inventory View**: Users can view the inventory of items in a store.
- **Purchase Items**: Users can buy items and pay the necessary amount.
- **Dashboard**: Users can view their credit and inventory on the dashboard.

## How does it works?
The application is based on input data from the user. To run the application, user must compile `App.java` file in the `cmd` with:
```
javac App.java
```
After that the user can run the application with:
```
java App.java
```
When the the app is up and running the user will be asked for a ***username***. After that he'll see the Menu UI where he can choose one option:

![menu](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/e6d905ce-7f86-4889-970a-b0630059ae0f)

If the customer chose the 1)Option - the Shop Inventory will open:

![shop](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/740ba984-a1df-443a-a671-8f948fbceb05)

On this page are indicated the ammount of different ***items***, their ***price***, their ***warrantee period*** and their ***category***. Also the customer can see the available ammount of ***money*** on the account. On that stage, the user can chose the item to buy (1x each time) or type `0` to return to the Menu page. 

Once the user bought some items, he can open his Dashboard to check his personal inventory. He must type `0` to return back to the Menu page, then type `2` to visualize the inventory:

![dashboard](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/945c0f1b-83e2-44e2-bf2b-18017517577b)

On the Dashboard the customer can see his ***profile name***, his ***balance*** and all the ***items*** in his inventory.

## Application structure
The application is executed from the `App.java` file which extends `UIManager.java`. `UIManager.java` is the main part of the application where all the methods are implemented.

`UIManager.java` imports vary packages:
- `Item.java` - ***Items*** abstarct standard class;
- `Consumabili.java` - ***Consumables*** abstract standard class;
- `Negozio.java` - ***Shop*** abstract standard class;
- `Oggetti.java` - ***Objects*** abstract standard class;
- `Servizzi.java` - ***Services*** abstract standard class;
- `Utente.java` - ***User*** abstract standard class.

All these packages contain useful functions and properties like:
- ***Private parameters***
- ***Get/Set methods*** to have the ability to modify them from the outside
- ***Public useful methods***

## Code structure
The `UIManager.java` imported libraries:

```
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
```


For the input manipulation is used `java.util.Scanner` library which makes easier the usage of IO with this method:

```
 public UIManager() {
        this.sc = new Scanner(System.in);
    }
```

Then making various `askInput` alternatives for `String`, `Int` and `Double` methods.

```
    public static String askInput() {
        System.out.print("-> ");
        return sc.nextLine();
    }
```

The method `public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi)` is used to show the user the inventory of a shop, and allows him to make purchases.

```
public static void negozio(Utente user, int sceltaNegozio, List<Negozio> listaNegozi){
        Negozio negozioCorrente = getNegozio(sceltaNegozio, listaNegozi);
        if (negozioCorrente != null) {
            boolean continuaAcquisti = true;
            do {
                System.out.println("Inventario del Negozio " + sceltaNegozio);
                
                for (Item item : negozioCorrente.getInventario()) {
                    if (item instanceof Consumabili) {
                        Consumabili consumabile = (Consumabili) item;
                        String str = calcoloScadenza(consumabile.getScadenza(), data);
                        System.out.println(consumabile.toString() + str);
                    }else {
                        System.out.println(item.toString());
                    }
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
```

The method `private static void acquistaOggetto(Utente user, Negozio negozio, int sceltaOggetto)` allows buying items from the shops.

```
private static void acquistaOggetto(Utente user, Negozio negozio, int sceltaOggetto){
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
```

The method `public static String calcoloScadenza(String scadenza, LocalDate data)` is used to calcolate the expiration date of a consumable item.

```
public static String calcoloScadenza(String scadenza, LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
        LocalDate dataAttuale = LocalDate.now();

        if (dataAttuale.isAfter(dataScadenza)) {
            return " (SCADUTO)";
        }else {
            return "";
        }
}
```

The method `public static int menuNegozio(double credito)` shows the customer the shop menu. From this menu, the user can choose which store to visit and make purchases.
```
public static int menuNegozio(double credito){
        System.out.println("""

                             ---MENU NEGOZI---
                    1)Brico     2)Esselunga     3)Centro massaggi
                             0)Torna indietro

                                Credito= """ + credito + """
                """);
        return askInputInt();
    }
}
```


The method `public void run()` is the main method of the `UIManager.java` which is used directly with the user. Basicaly, that's the innterface what the user see and use.

```
public void run(){
        List<Negozio> listaNegozi = new ArrayList<>();

        Oggetti oggetto1 = new Oggetti("Telecamera di sicurezza", 9, 99.90, "1 anno", TECH);
        Oggetti oggetto2 = new Oggetti("Metro", 25, 8.20, "1 mese", TOOL);
        Oggetti oggetto3 = new Oggetti("Trapano elettrico", 3, 45.00, "6 mesi", TECH);
        Oggetti oggetto4 = new Oggetti("Cacciavite", 12, 15.0, "1 mese", TOOL);
        Consumabili consumabile1 = new Consumabili("Coca-Cola", 3, 2.00, "2024-12-02", BEVANDE);
        Consumabili consumabile2 = new Consumabili("Panino", 6, 7.00, "2023-12-08", CIBO);
        Servizi servizio1 = new Servizi("Massaggio", 3, 30.00, "30 minuti");
        Servizi servizio2 = new Servizi("Massaggio", 3, 55.00, "1 ora");

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
```

## License

This project is licensed under the [I.Paguri License](LICENSE).
