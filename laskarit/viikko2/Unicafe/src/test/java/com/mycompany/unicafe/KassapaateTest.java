
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    

Maksukortti kortti = new Maksukortti(1000);
    private Kassapaate paate;


@Before//luodaan kortti alustuksena ennen jokaista testiä 

public void setUp() {

paate = new Kassapaate();

}


@Test

public void kassapaateOlemassa() {

assertTrue(paate != null);

}


@Test

public void konstruktoriAsettaaRahamaaranOikeinKunLounaitaMyyty0() {

//if((paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty())==0){

assertEquals(100000, paate.kassassaRahaa());

}


@Test

public void syöEdullisestiLisaaKassaanOikeinJosRahaaTarpeeksi() {

paate.syoEdullisesti(400);

paate.syoEdullisesti(100);

assertEquals(100240, paate.kassassaRahaa());


}


public void syöEdullisestiKassaPalauttaaOikeinVaihtorahan() {

assertEquals(60, paate.syoEdullisesti(300));

}


public void syöEdullisestiKassaPalauttaaOikeinVaihtorahanJosLiianVahan() {


assertEquals(20, paate.syoEdullisesti(20));

}


@Test

public void syöEdullisestiEilisaaKassaanJosRahaaLiianVahan() {

paate.syoEdullisesti(200);

assertEquals(100000, paate.kassassaRahaa());


}


@Test

public void syöMaukkaastiLisaaKassaanOikeinJOsRahaaTarpeeksi() {

paate.syoMaukkaasti(600);

assertEquals(100400, paate.kassassaRahaa());


}


@Test

public void syöMaukkaastiEilisaaKassaanJosRahaaLiianVahan() {

paate.syoMaukkaasti(200);

assertEquals(100000, paate.kassassaRahaa());


}


public void syömaukkaastiKassaPalauttaaOikeinVaihtorahan() {

assertEquals(200, paate.syoMaukkaasti(600));

}


public void syömaukkaastiKassaPalauttaaOikeinVaihtorahankunLiianVahan() {

assertEquals(10, paate.syoMaukkaasti(10));

}


@Test

public void JosMaksuRiittäväMaukkaidenLounaidenLkmKasvaa() {

paate.syoMaukkaasti(400);

paate.syoMaukkaasti(400);

paate.syoMaukkaasti(200);

paate.syoMaukkaasti(kortti);

assertEquals(3, paate.maukkaitaLounaitaMyyty());


}


@Test

public void JosMaksuRiittäväEdullistennLounaidenLkmKasvaa() {

paate.syoEdullisesti(240);

paate.syoEdullisesti(240);

paate.syoMaukkaasti(240);

paate.syoEdullisesti(100);

paate.syoEdullisesti(kortti);

assertEquals(3, paate.edullisiaLounaitaMyyty());


}


@Test

public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunPositiivinen() {

paate.lataaRahaaKortille(kortti, 400);

assertEquals(100400, paate.kassassaRahaa());


}


@Test

public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunNegatiivinen() {

paate.lataaRahaaKortille(kortti, -400);

assertEquals(100000, paate.kassassaRahaa());


}


@Test

public void booleanSyöEdullisestivahentaaKortiltaOikein() {

kortti.otaRahaa(240);

assertEquals(true, paate.syoEdullisesti(kortti));


}


@Test

public void booleanSyöEsullisestiOtaRahaaKortiltaKunEiSaldoa() {

kortti.otaRahaa(1000);

assertEquals(false, paate.syoEdullisesti(kortti));


}


@Test

public void booleanSyöMaukkaastiOttaaRahaaKortiltaKunOnSaldoa() {


assertEquals(true,paate.syoEdullisesti(kortti));


}

@Test

public void booleanSyöMaukkaastiEiOtaRahaaKortiltaKunEiSaldoa() {

kortti.otaRahaa(900);

assertEquals(false, paate.syoMaukkaasti(kortti));


}

@Test

public void kortilleRahaaLadattaessaKortinSaldoMuuttuuJaKassassaOlevaRahamääräKasvaaLdatullaSummalla() {//????????????

paate.lataaRahaaKortille(kortti, 20);

assertEquals(1020, kortti.saldo());

assertEquals(100020, paate.kassassaRahaa());

}}


