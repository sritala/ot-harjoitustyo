
package Wine.domain;

import Wine.dao.UserDao;
import Wine.dao.WineDao;
import Wine.domain.Wine;
import java.util.List;

/**
 *
 * @author susanna
 */

public class WineService {

    private WineDao wineDao;
    private UserDao userDao;
    private User loggedIn;

    public WineService(WineDao wineDao) {
        
        this.userDao = userDao;
        this.wineDao = wineDao;
    }

    /**
     * Uuden viinin lisääminen
     *
     * @param year luotavan viinin valmistusvuosi
     * @param country luotavan viinin valmistusmaa
     * @param name luotavan viinin nimi
     *
     * @return luonnin onnistumisstatus
     */
    
    public boolean createWine(int year, String country, String name) {
        Wine wine = new Wine(year, country, name);
        try {
            wineDao.create(wine);
//            System.out.println("Viini lisätty!");
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<Wine> getWines() {
        return wineDao.getAll();
    }

    public void delete() {
        wineDao.delete();
    }

//    public String getWines(){
//        List<Wine> wines = wineDao.getAll();
//        String apu = "";
//        for(Wine wine: wines){
//            apu += "Viinin id: " + wine.getId() + "\n";
//            apu += "Vuosi: " + wine.getYear() + "\n";
//            apu += "Valmistusmaa: " + wine.getCountry() + "\n";
//            apu += "Nimi: " + wine.getName() + "\n";
//        }
//        return apu;
//    }
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        loggedIn = user;

        return true;
    }

    /**
     * kirjautuneena oleva käyttäjä
     *
     * @return kirjautuneena oleva käyttäjä
     */

    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * uloskirjautuminen
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * uuden käyttäjän luominen
     *
     * @param username käyttäjätunnus
     * @param name käyttäjän nimi
     *
     * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false
     */
    
    public boolean createUser(String username, String name) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
