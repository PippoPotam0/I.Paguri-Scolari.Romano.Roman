/*Progettare un'applicazione che gestisce l'inventario di un negozio ed i suoi clienti. 
Un utente che apre l'applicazione può accedere come cliente inserendo un nome e poi visualizzare una lista di negozi. 
Ogni negozio ha un inventario di Item. Ogni Item ha un nome, una quantità ed un prezzo. 
Gli item si dividono in: Servizi (hanno anche un tempo di prestazione) 
Oggetti (hanno un tempo di garanzia ed una categoria (valore che ha dominio TECH, TOOL, FURNITURE) e 
Consumabili (hanno una data di scadenza ed una categoria (valore con dominio BEVANDE, CIBO).
L'utente ha un credito iniziale di 200$ e ogni volta che decide di comprare un Item, 
paga la somma necessaria a compare 1x Item selezionato. Ricapitolando, 
l'utente accede ad un negozio, vede i suoi item, sceglie se comprare un Item o tornare indietro, 
e nella dashboard iniziale vede il suo credito e il suo inventario (le cose che ha comprato). */


//Negozio = array di Item 
//Nome del negozio = nome dell'array
//Item = classe astratta
//Servizi, Oggetti, Consumabili = classi di Item con aggiuntivi attributi
//Utente = oggetto;

