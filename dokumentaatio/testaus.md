# Testausdokumentti

Ohjelmaa on testattu JUnit testeillä ja manuaalisesti komentorivin testeillä.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka

Pakkauksen wine.domain luokkia testaavat FakeUserDao, FakeWineDao sekä WineServiceTest, joiden määrittelevät testitapaukset simuloivat käyttöliittymän WineService- olioiden avulla suorittamia toiminnallisuuksia.

Sovelluslogiikkakerroksen luokille User ja Wine on tehty muutama testi testiluokissa FileUserDaoTest ja FileWineTest
joka kattaa mm. equals metodit. 

## DAO-luokat

DAO-luokkien toiminnallisuus on testattu luomalla tilapäinen TemporaryFolder tiedosto noudattaen Junitin sääntöjä. 

## Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 82% ja haarautumakattavuus 83%

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

## Asennus ja konfigurointi

Sovelluksen asennus on tehty sekä testattu ohjeiden mukaan sisältäen käynnistyshakemistossa sijaitsevan config.properties tiedoston. 

## Toiminnallisuudet

Määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Virheilmoitukset tulee mm. seuraavissa tilanteissa:
- Jos ei kirjoita oikeaa komentoa niin tulee virheellinen komento ilmoitus ennen kirjautumista ja kirjautumisen jälkeen.
- Luodessa käyttäjän käyttäjänimen tulee olla uniikki ja samaa käyttäjätunnusta ei voi luoda uudestaan. 
- Viinin vuosi tulee kirjoittaa numeroina. 
