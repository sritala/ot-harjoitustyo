/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Wine.domain.User;
import Wine.domain.WineService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WineServiceUserTest {
    
    FakeWineDao wineDao;
    FakeUserDao userDao;
    WineService service;
    
    @Before
    public void setUp() {
        wineDao = new FakeWineDao();
        userDao = new FakeUserDao();
    //    service = new WineService(wineDao, userDao);     
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("testertester");
        assertTrue(result);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Teppo Testaaja", loggedIn.getName() );
    }
    
    @Test
    public void loggedInUserCanLogout() {
        service.login("testertester");
        service.logout();
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = service.createUser("testertester", "Teuvo Testaaja");
        assertFalse(result);
    }
    
    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = service.createUser("dijkstra", "Edsger Dijkstra");
        assertTrue(result);
        
        boolean loginOk = service.login("dijkstra");
        assertTrue(loginOk);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Edsger Dijkstra", loggedIn.getName() );
    } 
}