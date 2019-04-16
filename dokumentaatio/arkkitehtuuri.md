# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri.png">

## Käyttöliittymä

tulossa

## Sovelluslogiikka

User kuvaa käyttäjiä ja Wine käyttäjien listaamia viinejä. Jokaisella käyttäjällä on uniikki käyttäjätunnus ja jokaisella viinillä on oma id ja listattuna sen tuontantovuosi, tuotantomaa sekä nimi. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka.png">

## käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu oikea käyttäjätunnus ja painetaan enteriä niin sovellus kirjautuu sisään. Sovelluksen kontrolli etenee seuraavasti:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavioOT.png">

## uuden viinin tekeminen

Kirjautunut käyttäjä pääsee luomaan viinin, jonka tiedot (vuosi, tuotantomaa, nimi) täytyy ilmoittaa, jotta onnistunut viini saadaan luotua viinilistaan. Sovelluksen kontrolli etenee seuraavasti:
<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavioOT1.png">
