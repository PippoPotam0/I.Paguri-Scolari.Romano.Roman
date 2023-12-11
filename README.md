# **Shop Inventory and Customer Management Application**

### **Authors:**
- Scolari Filippo
- Romano Davide
- Roman Vlad

## Introduction
This application is designed to manage the inventory of a store and its customers. A user opening the application can access it as a customer by entering a name and then view a list of stores. Each store has an inventory of items. Each item has a name, quantity, and price. Items are categorized into:

- **Services** (also have a performance time)
- **Objects** (have a warranty time and a category with the domain values: TECH, TOOL, FURNITURE)
- **Consumables** (have an expiration date and a category with the domain values: BEVERAGES, FOOD)

The user has an initial credit of $200, and each time they decide to buy an item, they pay the necessary amount to purchase 1x the selected item. To summarize, the user accesses a store, views its items, decides whether to buy an item or go back, and on the initial dashboard, sees their credit and inventory (the items they have bought).

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
When the the app is up and running the user will be asked for a username. After that he'll see the Menu UI where he can choose one option:

![menu](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/e6d905ce-7f86-4889-970a-b0630059ae0f)

If the customer chose the 1)Option - the Shop Inventory will open:

![shop](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/740ba984-a1df-443a-a671-8f948fbceb05)

On this page are indicated the ammount of different ***items***, their ***price***, their ***warrantee period*** and their ***category***. Also the customer can see the available ammount of ***money*** on the account. On that stage, the user can chose the item to buy (1x each time) or type `0` to return to the Menu page. 

Once the user bought some items, he can open his Dashboard to check his personal inventory. He must type `0` to return back to the Menu page, then type `2` to visualize the inventory:

![dashboard](https://github.com/PippoPotam0/I.Paguri-Scolari.Romano.Roman/assets/153176955/945c0f1b-83e2-44e2-bf2b-18017517577b)

On the Dashboard the customer can see his ***profile name***, his ***balance*** and all the ***items*** in his inventory.

## Application structure
The application is executed from the `App.java` file with extends `UIManager.java`. `UIManager.java` is the main part of the application where all the methods are implemented.

`UIManager.java` imports vary packages:
- `Item.java` - Items abstarct standard class;
- `Consumabili.java` - Consumables abstract standard class;
- `Negozio.java` - Shop abstract standard class;
- `Oggetti.java` - Objects abstract standard class;
- `Servizzi.java` - Services abstract standard class;
- `Utente.java` - User abstract standard class.

All these packages contain useful functions and properties like:
- Private parameters
- Get/Set methods to have the ability to modify them from the outside
- Public useful methods



## License

This project is licensed under the [I.Paguri License](LICENSE).
