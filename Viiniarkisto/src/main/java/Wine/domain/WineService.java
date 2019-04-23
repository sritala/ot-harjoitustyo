package Wine.domain;

import Wine.dao.UserDao;
import Wine.dao.WineDao;
import java.util.List;

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
     */
    public void createWine(int year, String country, String name) {
        String user = loggedIn.getUsername();
        Wine wine = new Wine(year, country, name, user);
        try {
            wineDao.create(wine);
        } catch (Exception ex) {
            System.out.println("Errori: " + ex);;
        }
    }

    public List<Wine> getWines() {
        return wineDao.getAll();
    }

    /**
     * Viinin poistaminen
     *
     * @param name Poistettavan viinin nimi
     */
    public void delete(String name) {
        try {
            wineDao.delete(name);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

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
     * @param year muokattavan viinin valmistusvuosi
     * @param country muokattavan viinin valmistusmaa
     * @param name muokattavan viinin nimi
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
    public boolean createUser(String username) {
        if (userDao.findByUsername(username) != null) {
            System.out.println("Käyttäjä jo olemassa, valitse toinen käyttäjänimi");
            return false;
        }
        User newUser = new User(username);
        try {
            userDao.create(newUser);
            login(newUser.getUsername());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
