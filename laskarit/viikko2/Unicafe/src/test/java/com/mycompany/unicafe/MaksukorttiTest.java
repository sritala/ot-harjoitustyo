package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void KortinSaldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void RahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertTrue(kortti.saldo() == 15);

    }

    @Test
    public void SaldoVaheneeOikein() {
        kortti.otaRahaa(10);
        assertTrue(kortti.saldo() == 0);
    }

    @Test
    public void SaldoEiMuutu() {
        kortti.otaRahaa(20);
        assertTrue(kortti.saldo() == 10);
    }

}
