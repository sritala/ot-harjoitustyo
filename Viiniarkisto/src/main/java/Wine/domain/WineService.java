package Wine.domain;

import Wine.dao.UserDao;
import Wine.dao.WineDao;
import Wine.domain.Wine;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author susanna
 */
public class WineService {

    private WineDao wineDao;
    private UserDao userDao;
    private User loggedIn;

    public WineService(WineDao wineDao, UserDao userDao) {

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

    /**
     * Viinin poistaminen
     */
    public void delete(String name) {
        try {
            wineDao.delete(name);
        } catch (Exception ex) {
            System.out.println(ex);
        }
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
    /**
     * Kirjautuminen käyttäjätunnuksella
     *
     * @param username luotavan käyttäjän tunnus
     *
     * @return luonnin onnistumisstatus
     */
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;

        }
        loggedIn = user;

        return true;
    }

    public void create(String username) throws Exception {
        User newUser = new User(username);
        userDao.create(newUser);
        login(newUser.getUsername());
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
     * Viinin muokkaaminen
     *
     * @param id viinin id
     * @param year luotavan viinin valmistusvuosi
     * @param country luotavan viinin valmistusmaa
     * @param name luotavan viinin nimi
     */
    public void updateWine(int id, int year, String country, String name) {
        try {
            wineDao.updateWine(id, year, country, name);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * uuden käyttäjän luominen
     *
     * @param username käyttäjätunnus
     *
     * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false
     */
//    public boolean createUser(String username, String name) {
//        if (userDao.findByUsername(username) != null) {
//            return false;
//        }
//        User user = new User(username);
//        try {
//            userDao.create(user);
//        } catch (Exception e) {
//            return false;
//        }
//
//        return true;
//    }
}
