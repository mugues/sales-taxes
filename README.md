Tech test per B******
=======

Descrizione
-----------
Il progetto è stato realizzato con jdk 8 e mvn3 per la compilazione/esecuzione dei test.

Paragraphs are separated by a blank line.

Text attributes *Italic*, **bold**, `monospace`.

Moduli:
	* sales-taxes				--> contiene il project
 	
 	
Installazione 
-------------
 	
mvn clean install	


Esecuzione
-------------
mvn test

Il test unitario testProcessOrder del junit test CheckoutServiceTest fornisce l'input come da specifica verso il componente CheckoutService che implementa la logica di output.
La classe OrderImpl che modella un ordine viene fornita anche con un esempio di utilizzo di functional interface per il calcolo dei totali e delle tasse totali.
Nello specifico i metodi getSalesTaxesLambda e getTotalLambda.

A corredo il test unitario CheckoutServiceTest contiene altri test per la validazione dell'input.
Viene anche fornito un ulteriore test unitario BasketItemImplTest utilizzato durante lo sviluppo della soluzione target come esempio di TDD.

L'output viene scritto sull'appender application.log; di seguito un esempio dell'output prodotto dall'esecuzione



2014-11-25 16:05:58,527 DEBUG [CheckoutServiceTest]  
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceTest] Output 1: 
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceImpl] processOrder start 
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceImpl] processing 3 bask items 
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceImpl] 1 book : 12.49 
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceImpl] 1 music CD : 16.49 
2014-11-25 16:05:58,527 DEBUG [CheckoutServiceImpl] 1 chocolate bar : 0.85 
2014-11-25 16:05:58,748 DEBUG [CheckoutServiceImpl] Sales Taxes: 1.50 
2014-11-25 16:05:58,765 DEBUG [CheckoutServiceImpl] Total: 29.83 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceImpl] Sales Taxes lambda: 1.50 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceImpl] Total lambda: 29.83 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceImpl] processOrder stop 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceTest]  
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceTest] Output 2: 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceImpl] processOrder start 
2014-11-25 16:05:58,819 DEBUG [CheckoutServiceImpl] processing 2 bask items 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 imported box of chocolates : 10.50 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 imported bottle of perfume  : 54.65 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Sales Taxes: 7.65 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Total: 65.15 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Sales Taxes lambda: 7.65 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Total lambda: 65.15 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] processOrder stop 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceTest]  
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceTest] Output 3: 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] processOrder start 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] processing 4 bask items 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 imported bottle of perfume : 32.19 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 bottle of perfume : 20.89 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 packet of headache pills : 9.75 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] 1 box of imported chocolates  : 11.85 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Sales Taxes: 6.70 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Total: 74.68 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Sales Taxes lambda: 6.70 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] Total lambda: 74.68 
2014-11-25 16:05:58,829 DEBUG [CheckoutServiceImpl] processOrder stop 

