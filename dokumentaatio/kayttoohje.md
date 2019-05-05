
# Käyttöohje

Lataa tiedosto Viiniarkisto.jar

# Konfigurointi

Config.properties sijaitsee käynnistyshakemistossa, joka määrittelee käyttäjät ja viinit seuraaviin tiedostoihin:

- fileUser = users.txt
- fileWine = wines.txt

# Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla:

java -jar target/Viiniarkisto.jar

## Kirjautuminen

Sovellus käynnistyy seuraavaan näkymään. Sovelluksen käyttäjä voi luoda uuden käyttäjän tai kirjautua sisään. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohjeet.png" width="400">

## Uuden viinin luominen

Onnistunut kirjautuminen vie seuraavaan näkymään. Kirjautunut käyttäjä voi luoda viinin valitsemalla komennon yksi. Viini tulee määritellä sen valmistusvuoden, tuotantomaan, nimen, rypälelajin sekä ruokaehdotuksen mukaan. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/addwine.png" width="400">

## Viinien listautuminen

Komennolla kaksi käyttäjä voi nähdä kaikki luodut viinit listattuna ja viinien tiedot. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/listwines.png">


## Viinin poistaminen

Komennolla 3 voi poistaa olemassa olevan viinin. Viini tulee valita sen nimen perusteella. Sen jälkeen viini poistuu listauksesta. 

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/removewine.png">

## Viinin muokkaaminen

Komennolla neljä voi muokata olemassa olevaa viiniä. Viini tulee valita sen id:n perusteella.

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/modifywine.png">

Kirjoittamalla komennon "X" käyttäjä voi kirjautua ulos sekä sillä voi lisäksi sulkea sovelluksen kirjautumisnäkymässä. 

