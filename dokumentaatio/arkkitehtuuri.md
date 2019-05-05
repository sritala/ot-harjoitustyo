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

Lisää viini komennolla käyttäjä luo Viiniarkistoonsa uuden viinin, joka tulee määritellä sen valmistusvuoden, tuotantomaan, nimen, rypälelajin sekä ruokaehdotuksen mukaan. Viinien listauksessa näkyy luodut viinit, joka edellyttää ensin ainakin yhden viinin luomista. Jos viiniarkistossa on viini niin sitä voi muokata ja poistaa arkistosta. 

## Sovelluslogiikka

User kuvaa käyttäjiä ja Wine käyttäjien listaamia viinejä. Jokaisella käyttäjällä on uniikki käyttäjätunnus ja jokaisella viinillä on oma id ja sille listattuna sen tuontantovuosi, tuotantomaa, nimi, rypälelaji sekä ruoka.  

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka.png">

WineService vastaa toiminnallisista kokonaisuuksista ja tarjoaa muun muassa seuraavanlaisia metodeita.

Viineille:
- boolean createWine(int year, String country, String name, String adder)
- void deleteWine(String name)

Käyttäjälle:
- boolean login(String username)
- void create(String username)
- void logout()

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka2.png">


## käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu oikea käyttäjätunnus ja painetaan enteriä niin sovellus kirjautuu sisään. Sovelluksen kontrolli etenee seuraavasti:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavioOT.png">

## uuden viinin tekeminen

Kirjautunut käyttäjä pääsee luomaan viinin, jonka tiedot (vuosi, tuotantomaa, nimi) täytyy ilmoittaa, jotta onnistunut viini saadaan luotua viinilistaan. Sovelluksen kontrolli etenee seuraavasti:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavioOT1.png">

# Ohjelman rakenteeseen jääneet heikkoudet

## Käyttöliittymä

Käyttöliittymässä voisi olla enemmän tietoturvaa parantavia asioita kuten salasanan luonti. Lisäksi tekstikäyttöliittymän olisi voinut korvata graafisella käyttöliittymällä, joka saattaisi olla käyttäjälle selkeämpi. Checkstyle pisteitä kertyi myös Ui:n pitkistä start ja setup metodeista, joita olisi voinut jakaa pienempiin metodeihin. 

## DAO-luokat

FileDao luokat sisältävät paljon koodia, jota olisi voinut erotella omiin luokkiin. 
