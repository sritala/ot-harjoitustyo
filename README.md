# Viiniarkisto

Tämä on harjoitustyö Ohjelmistotekniikan kurssille 


[Käyttöohje](https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/sritala/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/sritala/ot-harjoitustyo/tree/master/dokumentaatio/vaatimusmaarittely)

[Työaikakirjanpito](https://github.com/sritala/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Kirjautuminen

Toimii tällä hetkellä vain "susanna" käyttäjällä

## Komentorivitoiminnot

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

