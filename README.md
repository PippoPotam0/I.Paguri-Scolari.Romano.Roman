# **_Shop Inventory and Customer Management Application_**

### **_Authors:_**
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
The application is based on input data from the user. To run the application, user must compile `App.java` file.

## License

This project is licensed under the [I.Paguri License](LICENSE).
