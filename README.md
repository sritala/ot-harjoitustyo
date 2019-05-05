# Viiniarkisto

Tämä on harjoitustyö Ohjelmistotekniikan kurssille 


[Käyttöohje](https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/sritala/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 7](https://github.com/sritala/ot-harjoitustyo/releases/tag/Viikko7)


## Komentorivitoiminnot

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Viiniarkisto-1.0-SNAPSHOT.jar_

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/sritala/ot-harjoitustyo/blob/master/Viiniarkisto/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

