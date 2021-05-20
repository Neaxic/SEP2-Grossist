<h1>Hvad kan DAOInterface?</h1>
<hr>

<h2>Fra Databasen til ServerModellen</h2>
<h3>Kunder</h3>

* En list af alle varer (List<<i>Product</i>>)


<h3>Grossist</h3>

* En liste af alle varer samt antallet på lager (Pair<<i>Product</i>, <i>Integer</i>>)


<br>

<h2>Fra ServerModellen til Databasen</h2>
<h3>Kunder</h3>

* En order (int, LocalDateTime, Basket)
    * Laver både order og orderSpec


<h3>Grossist</h3>

* En varer samt antallet af varen (Pair<<i>Product</i>, <i>Integer</i>>)
* Ændre værdi på en vare (Pair<<i>Product</i>, <i>Integer</i>>)