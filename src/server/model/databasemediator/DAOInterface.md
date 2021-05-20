<h1>Hvad kan DAOInterface?</h1>
DAOInterface består af 2 forskellige interfaces, <i>DAOCustomerInterface</i> og <i>DAOGrosserInterface</i>, som bruges af ServerModellen til at kommunikere med den DAOImplementation der er oprettet.
Pointen bag de interfaces er, at vi kan dele funktionaliteten som Kunderne og Grossister skal bruge og så have give mulighed for, at man kan udskifte sin DAOImplementation til at være enten én eller to klasser.
<br>
I dette dokument, kan du læse om hvad de forskellige interfaces skal kunde, primært delt op mellem om der er ændring på databasen eller bare at bede om information fra databasen.
<hr>

<h2>Anmodning om Information fra Databasen (GETTING)</h2>
<h3>DAOCustomerInterface</h3>

* En list af alle varer (List<<i>Product</i>>)

<h3>DAOGrosserInterface</h3>

* En liste af alle varer samt antallet på lager (Pair<<i>Product</i>, <i>Integer</i>>)
  
* En liste af alle ordre (list<<i>Order</i>>)

<br>

<h2>Ændring af Information på Databasen (SETTING)</h2>
<em>Her skal der overvejes, om det er nødvendigt at sætte en lås på metoder.</em>

<h3>DAOCustomerInterface</h3>

* Oprette en order (int, LocalDateTime, Basket)
	* Laver både order (cvr, orderTime, orderSum) og orderSpec (orderNo, productID, antal)

<h3>DAOGrosserInterface</h3>

* Oprette en varer samt antallet af varen (Pair<<i>Product</i>, <i>Integer</i>>)

* Ændre antal af en vare (Pair<<i>Product</i>, <i>Integer</i>>)

* Fjerne en vare helt (Product)

* Oprette kunder (int, String, String, String)
	* (cvr, navn, kodeord, adresse)