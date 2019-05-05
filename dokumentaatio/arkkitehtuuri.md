# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/rakenne.png">

Pakkaus wine.ui sisältää tekstikäyttöliittymällä toteutetun käyttöliittymän. Wine.domain sovelluslogiikan ja wine.dao tietojen tallennuksesta vastaavan koodin. 

## Käyttöliittymä

Käyttöliittymän näkymät ovat seuraavat: 

- Kirjautuminen
- Uuden viinin luominen
- Viinilista

Käyttöliittymä on rakennettu Ui- luokkaan ja noudattaa tekstikäyttöliittymää. Tekstikäyttöliittymässä sovelluksen käyttäjälle annetaan lista komennoista, joita käyttäjää pyydetään käyttämään. Sovelluksen vaihtoehto komennot ovat ensiksi seuraavat:

- 1 kirjaudu sisään
- 2 luo uusi käyttäjä
- x lopeta

Tässä näkymässä käyttäjä saa mahdollisuuden luoda uuden käyttäjätunnuksen tai kirjautua jo olemassa olevalla sisään. Kun kirjautuminen on suoritettu onnistuneesti niin sovelluksessa siirrytään seuraavaan henkilökohtaiseen Viiniarkisto näkymään: 

- 1 lisää viini 
- 2 listaa viinit
- 3 poista viini
- 4 muokkaa viiniä
- x kirjaudu ulos

Lisää viini komennolla käyttäjä luo Viiniarkistoonsa uuden viinin, joka tulee määritellä sen valmistusvuoden, tuotantomaan, nimen, rypälelajin sekä ruokaehdotuksen mukaan. Viinien listauksessa näkyy luodut viinit, joka edellyttää ensin ainakin yhden viinin luomista. Jos viiniarkistossa on viini niin sitä voi muokata tai poistaa arkistosta. 

## Sovelluslogiikka

User kuvaa käyttäjiä ja Wine käyttäjien listaamia viinejä. Jokaisella käyttäjällä on uniikki käyttäjätunnus ja jokaisella viinillä on oma id ja sille listattuna sen tuontantovuosi, tuotantomaa, nimi, rypälelaji sekä ruoka. Relaatiomallista nähdään, että taulujen suhde on yhden suhde moneen eli yhdellä käyttäjällä voi olla monta viiniä. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka%20(1).png">

WineService vastaa toiminnallisista kokonaisuuksista ja tarjoaa muun muassa seuraavanlaisia metodeita.

Viineille:
- boolean createWine(int year, String country, String name, String adder, String grape, String food)
- void updateWine (int id, int year, String country, String name, String grape, String food)
- void deleteWine(String name)
- List<Wine> getWines()

Käyttäjälle:
- boolean login(String username)
- void create(String username)
- void logout()

WineService pääsee käsiksi käyttäjien sekä viinien tietoihin rajapintojen WineDao sekä UserDao luokkien kautta, jotka sijaitsevat pakkauksessa wine.dao. Konstruktorikutsujen yhteydessä tiedot siirtyvät sovelluslogiikalle. Wineservicen ja sovelluksen muiden osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka2%20(1).png">

## Tietojen pysyväistallennus

Pakkauksen wine.dao luokat FileWineDao ja FileUserDao huolehtivat tietojen tallettamisesta tiedostoihin. Luokat noudattavat siis Data Access Object -suunnittelumallia ja luokat ovat eristettyinä rajapintojen WineDao ja UserDao taakse, joita sovelluslogiikka käyttää. Testeissä hyödynnetään FileWineDao sekä FileUserDao luokkia. 

## Tiedostot

Sovellus tallentaa käyttäjien ja viinien tiedot niille erillisiin tarkoitettuihin tiedostoihin. Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

Sovellus tallentaa käyttäjät seuraavassa formaatissa eli käyttätunnuksen perusteella: 

- susanna;
- masa;
- Maija;

Käyttäjien viinien tallentavan tiedoston formaatti on seuraava:

- 1;2013;Italia;Castello di Brolio;Maija;Valkoviini;Vahvat juustot
- 2;2017;Espanja;Quinta de Aves Tempranillo;Maija;Punaviini / Tempranillo;Tapakset ja erilaiset liharuoat 

Puolipisteellä erotetaan viinin tiedot seuraavasti:
- Viinin id;vuosi;maa;nimi;käyttäjä;rypälelaji;ruoka

## Päätoiminnallisuudet

Toimintalogiikka on kuvattu seuraavaksi kahdessa sekvenssikaaviossa muutaman päätoiminnallisuuden osalta. 

### Käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on valittu kirjautumiskomento ja kirjoitettu käyttätunnus oikein niin sovelluksen kontrolli etenee seuraavasti:

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio2%20.png"> 

Sovelluslogiikka selvittää UserDao:n avulla onko käyttäjätunnus olemassa. Jos on ja kirjautuminen onnistuu niin käyttöliittymä vaihtuu sovelluksen varsinaiseen Viiniarkisto tekstikäyttöliittymä näkymään, jossa voi toteuttaa sovelluksen CRUD toiminnot. 

## Uuden käyttäjän luominen 

Kun on painettu komentoa uuden käyttäjän luominen ja näkymään on syötetty käyttäjätunnus joka ei ole käytössä niin createUser etenee sovelluksen kontrollin mukaan seuraavasti: 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio3.png">

Tapahtumakäsittelijä kutsuu sovelluslogiikan metodia createUser antaen parametriksi luotavan käyttäjän käyttäjätunnuksen. Sovelluslogiikka selvittää userDao:n avulla onko käyttäjätunnus olemassa. Jos käyttäjän luominen on mahdollista ja käyttäjätunnusta ei ole vielä valittu niin sovelluslogiikka luo User-olion ja tallentaa sen kutsumalla UserDao:n metodia create.

## Uuden viinin tekeminen

Kirjautunut käyttäjä pääsee luomaan viinin, jonka tiedot (vuosi, tuotantomaa, nimi, rypälelaji, ruoka) täytyy ilmoittaa, jotta onnistunut viini saadaan luotua viinilistaan. Sovelluksen kontrolli etenee seuraavasti:

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio4.png">

Huom! Sovellus on muuttunut kaavion tekemisestä, jolloin tietoihin on lisätty vaatimukseksi myös rypälelaji sekä ruoka.

Tapahtumankäsittelijä kutsuu sovelluslogiikan metodia addWine antaen parametreiksi luotavan viinin tiedot. Sovelluslogiikka luo uuden Wine-olion ja tallentaa sen kutsumalla WineDao:n metodia create. Käyttöliittymä taas listaa näytettävät viinit  kutsumalla omaa metodiaan printWines ja printWineById. 

## Muut toiminnallisuudet

Muut toiminnalisuudet toimivat samalla periaatteella kun tapahtumankäsittelijä kutsuu sopivaa sovelluslogiikan metodia ja sovelluslogiikka päivittää viinilistaa ja kirjautuneen käyttäjän tilaa. 

# Ohjelman rakenteeseen jääneet heikkoudet

## Käyttöliittymä

Käyttöliittymässä voisi olla enemmän tietoturvaa parantavia asioita kuten salasanan luonti. Lisäksi tekstikäyttöliittymän olisi voinut korvata graafisella käyttöliittymällä, joka saattaisi olla käyttäjälle selkeämpi. Checkstyle pisteitä kertyi myös Ui:n pitkistä start ja setup metodeista, joita olisi voinut jakaa pienempiin metodeihin. 

## DAO-luokat

FileDao luokat sisältävät paljon koodia, jota olisi voinut erotella omiin luokkiin. 
