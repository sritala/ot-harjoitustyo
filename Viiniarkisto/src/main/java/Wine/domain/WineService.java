package Wine.domain;

import Wine.dao.UserDao;
import Wine.dao.WineDao;
import java.util.List;

/**
 * Sovelluslogiikasta vastaava luokka
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
     * @param grape luotavan viinin laji
     * @param food viinin ruokaehdotus
     * @throws java.lang.Exception
     */
    public void createWine(int year, String country, String name) throws Exception {
        String user = loggedIn.getUsername();
        Wine wine = new Wine(year, country, name, user);
        wineDao.create(wine);
    }

    public List<Wine> getWines() {
        return wineDao.getAll();
    }

    /**
     * Viinin poistaminen
     *
     * @param name Poistettavan viinin nimi
     * @throws java.lang.Exception
     */
    public void delete(String name) throws Exception {
        wineDao.delete(name);
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
     * @param grape muokattavan viinin laji
     * @param food muokattavan viinin ruokaehdotus
     * @throws java.lang.Exception
     */
    public void updateWine(int id, int year, String country, String name) throws Exception {
        wineDao.updateWine(id, year, country, name);
    }

    /**
     * uuden käyttäjän luominen
     *
     * @param username käyttäjätunnus
     *
     * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false
     * @throws java.lang.Exception
     */
    public boolean createUser(String username) throws Exception {
        if (userDao.findByUsername(username) != null) {
            System.out.println("Käyttäjä jo olemassa, valitse toinen käyttäjänimi");
            return false;
        }
        User newUser = new User(username);
        userDao.create(newUser);
        login(newUser.getUsername());
        return true;
    }

}
