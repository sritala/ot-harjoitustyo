/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import wine.domain.User;
import wine.domain.Wine;
import wine.domain.WineService;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WineServiceTest {

    FakeWineDao wineDao;
    FakeUserDao userDao;
    WineService service;

    @Before
    public void setUp() throws Exception {
        wineDao = new FakeWineDao();
        userDao = new FakeUserDao();
        Wine w1 = new Wine(2013, "Italia", "Toscana", "Pekka","Punaviini", "Pasta");
        Wine w2 = new Wine(2016, "Espanja", "Rioja", "Pekka", "Punaviini", "Liha");
        User u1 = new User("testi");
        userDao.create(u1);
        wineDao.create(w1);
        wineDao.create(w2);
        service = new WineService(wineDao, userDao);
    }

    @Test
    public void atStartListContainsTwoWinesAndReturnsCorrectSecondWine() {
        List<Wine> wines = service.getWines();
        assertEquals(2, wines.size());
        Wine wine2 = wines.get(1);
        assertEquals("Rioja", wine2.getName());

    }

    @Test
    public void removingWineReturnsOneLessInSize() throws Exception {
        wineDao.delete("Toscana");
        List<Wine> wines = service.getWines();
        assertEquals(1, wines.size());
    }

    @Test
    public void nonExistingUserCanNotLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);

        assertEquals(null, service.getLoggedUser());
    }

    @Test
    public void updateChangesWineCorrectly() throws Exception {
        service.updateWine(0, 2015, "Italia", "Jotain", "Punaviini", "Pasta");
        List<Wine> wines = service.getWines();
        Wine newFirst = wines.get(0);
        assertEquals(2015, newFirst.getYear());
        assertEquals("Italia", newFirst.getCountry());
        assertEquals("Jotain", newFirst.getName());
    }

    @Test
    public void deletingWineWorks() throws Exception {
        List<Wine> wines = service.getWines();
        int length = wines.size();
        service.delete("Toscana");
        assertEquals(length - 1, service.getWines().size());

    }

    @Test
    public void creatingWineWorks() throws Exception {
        User first = userDao.findByUsername("testi");
        service.login(first.getUsername());
        List<Wine> wines = service.getWines();
        int length = wines.size();
        service.createWine(2019, "Testimaa", "Testiviini", "Testirypäle", "Testiruoka");
        assertEquals(length + 1, service.getWines().size());
    }

    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("testi");
        assertTrue(result);
        User loggedIn = service.getLoggedUser();
        assertEquals("testi", loggedIn.getUsername());
    }

    @Test
    public void loggedInUserCanLogout() {
        service.login("testi");
        service.logout();

        assertEquals(null, service.getLoggedUser());
    }

    @Test
    public void canCreateNewUser() throws Exception {
        assertEquals(true, service.createUser("testi2"));
    }
     @Test
    public void cantCreateNewUserWithExistingUsername() throws Exception {
        assertEquals(false, service.createUser("testi"));
    }

}
