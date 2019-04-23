/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Wine.domain.User;
import Wine.domain.Wine;
import Wine.domain.WineService;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        Wine w1 = new Wine(2013, "Italia", "Toscana", "Pekka");
        Wine w2 = new Wine(2016, "Espanja", "Rioja", "Pekka");
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
    public void existingUserCanLogIn() {
        boolean result = service.login("testi");
        assertTrue(result);
        User loggedIn = service.getLoggedUser();
        assertEquals("Testi", loggedIn.getUsername());
    }

    @Test
    public void loggedInUserCanLogout() {
        service.login("testi");
        service.logout();

        assertEquals(null, service.getLoggedUser());
    }

}
