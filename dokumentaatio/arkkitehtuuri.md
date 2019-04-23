# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri.png">

## Käyttöliittymä

Käyttöliittymän näkymät ovat seuraavat: 

- Kirjautuminen
- Uuden viinin luominen
- Viinilista

Käyttöliittymä on rakennettu WineArchiveen ja noudattaa tekstikäyttöliittymää. Ensimmäiseksi sovellus pyytää kirjautumaan sisään ja onnistuneen kirjautumisen jälkeen se siirtyy käyttäjän Viiniarkistoon. Viiniarkistossa käyttäjä saa vaihtoehdot:

- 1 lisää viini 
- 2 listaa viinit
- 3 poista viini
- x kirjaudu ulos

## Sovelluslogiikka

User kuvaa käyttäjiä ja Wine käyttäjien listaamia viinejä. Jokaisella käyttäjällä on uniikki käyttäjätunnus ja jokaisella viinillä on oma id ja listattuna sen tuontantovuosi, tuotantomaa sekä nimi. 

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
