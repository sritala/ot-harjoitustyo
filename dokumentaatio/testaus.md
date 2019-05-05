# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka

Pakkauksen wine.domain luokkia testaavat FakeUserDao, FakeWineDao sekä WineServiceTest, joiden määrittelevät testitapaukset simuloivat käyttöliittymän WineService- olioiden avulla suorittamia toiminnallisuuksia.

Sovelluslogiikkakerroksen luokille User ja Wine on tehty muutama yksikkötesti kattamaan tapaukset, joita integraatiotestit eivät kata (mm. olioiden equals-metodit).

## DAO-luokat
Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä tilapäinen tiedosto hyödyntäen JUnitin TemporaryFolder-ruleja.

## Testauskattavuus
Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 82% ja haarautumakattavuus 83%

<img src="https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png">

## Järjestelmätestaus
Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

## Asennus ja konfigurointi
Sovellus on haettu ja sitä on testattu käyttöohjeen kuvaamalla tavalla sekä OSX- että Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen config.properties-tiedosto.

## Toiminnallisuudet

Määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Virheilmoitukset tulee mm. seuraavissa tilanteissa:
- Jos ei kirjoita oikeaa komentoa niin tulee virheellinen komento ilmoitus ennen kirjautumista ja kirjautumisen jälkeen.
- Luodessa käyttäjän käyttäjänimen tulee olla uniikki ja samaa käyttäjätunnusta ei voi luoda uudestaan. 
- Viinin vuosi tulee kirjoittaa numeroina. 
