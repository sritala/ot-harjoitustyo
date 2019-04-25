package Wine.ui;

import Wine.dao.FileUserDao;
import Wine.domain.WineService;
import Wine.dao.FileWineDao;
import Wine.domain.Wine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * Sovelluslogiikasta vastaava luokka
 */
public class WineArchive {

    private Scanner input;
    private Map<String, String> inputs;
    private Map<String, String> setup;
    private WineService wineService;

    public WineArchive(Scanner input) throws FileNotFoundException, IOException, Exception {
        this.input = input;
        inputs = new TreeMap<>();
        setup = new TreeMap<>();
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));

//        String wineFile = properties.getProperty("wineFile");
        FileWineDao wineDao = new FileWineDao("wines.txt");
        FileUserDao userDao = new FileUserDao("users.txt");
        wineService = new WineService(wineDao, userDao);

        setup.put("1", "1 kirjaudu sisään");
        setup.put("2", "2 luo uusi käyttäjä");
        setup.put("x", "x lopeta");

        inputs.put("x", "x kirjaudu ulos");
        inputs.put("1", "1 lisää viini");
        inputs.put("2", "2 listaa viinit");
        inputs.put("3", "3 poista viini");
        inputs.put("4", "4 muokkaa viiniä");
    }

    /**
     * Jos kirjautuminen on onnistunut
     *
     */
    public void setup() throws Exception {
        System.out.println("Tervetuloa!");
        System.out.println("");
        System.out.println("Valitse yksi seuraavista vaihtoehdoista:");
        while (true) {
            System.out.println();
            printSetupInstruction();
            System.out.println();
            System.out.print("komento: ");
            String command = input.nextLine();
            if (!setup.keySet().contains(command)) {
                System.out.println("virheellinen komento.");
            }

            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                System.out.print("Käyttäjätunnus: ");
                String user = input.nextLine();
                wineService.login(user);
                if (wineService.getLoggedUser() != null) {
                    start();
                }

            } else if (command.equals("2")) {
                System.out.print("Uusi käyttäjätunnus: ");
                String newUser = input.nextLine();
                wineService.createUser(newUser);
                if (wineService.getLoggedUser() != null) {
                    start();

                }
            }

        }

    }

    /**
     * Käynnistyminen
     */

    public void start() throws Exception {
        System.out.println("");
        System.out.println("Viiniarkisto");
        System.out.println("");
        System.out.println("Hei " + wineService.getLoggedUser().getUsername() + ",");
        while (true) {
            printInstruction();
            System.out.println();
            System.out.print("komento: ");
            String command = input.nextLine();
            if (!inputs.keySet().contains(command)) {
                System.out.println("virheellinen komento.");
            }

            if (command.equals("x")) {
                wineService.logout();
                break;
            } else if (command.equals("1")) {
                addWine();
            } else if (command.equals("2")) {
                printWines();
            } else if (command.equals("3")) {
                delete();
            } else if (command.equals("4")) {
                updateWine();
            }

        }
    }

    public void printSetupInstruction() {
        System.out.println();
        for (String command : this.setup.values()) {
            System.out.println(command);
        }
    }

    public void printInstruction() {

        System.out.println();
        for (String command : this.inputs.values()) {
            System.out.println(command);

        }

    }

    public void printWines() {
        List<Wine> wineList;
        wineList = getWines();

        String currentUser = wineService.getLoggedUser().getUsername();

        for (int i = 0; i < wineList.size(); i++) {
            Wine wine = wineList.get(i);
            if (wine.getAdder().equalsIgnoreCase(currentUser)) {
                printWine(wine);
            }
        }
    }

    /**
     * Viinien listautuminen
     *
     * @param year luotavan viinin tuotantovuosi
     * @param country luotavan viinin tuotantomaa
     * @param name luotavan viinin nimi
     * @param grape luotavan viinin rypälelaji
     * @param food luotavan viinin ruokaehdotus
     */
    public void printWine(Wine wine) {
        System.out.println("Id: " + wine.getId());
        System.out.println("Nimi: " + wine.getName());
        //System.out.println("Rypälelaji:" +wine.getGrape());
        System.out.println("Valmistusvuosi: " + wine.getYear());
        System.out.println("Tuotantomaa: " + wine.getCountry());
        //System.out.println("Ruokaehdotus: " + wine.getFood());

        System.out.println();
    }

    /*
    *Viinien listautuminen indeksittäin
     */
    public void printWineById(int index) {
        List<Wine> wineList;
        wineList = getWines();
        Wine printableWine = wineList.get(index);
        printWine(printableWine);
    }

    /**
     * Uuden viinin lisääminen kirjautuneena olevalle käyttäjälle
     *
     * @param year luotavan viinin tuotantovuosi
     * @param country luotavan viinin tuotantomaa
     * @param name luotavan viinin nimi
     * @param grape luotavan viinin rypälelaji
     * @param food luotavan viinin ruokaehdotus
     */
    public void addWine() throws Exception {
        System.out.print("Viinin vuosi: ");
        int year = Integer.parseInt(input.nextLine());
        System.out.print("Viinin tuotantomaa: ");
        String country = input.nextLine();
        System.out.print("Viinin nimi: ");
        String name = input.nextLine();
        // System.out.println("Viinin rypälelaji: ");
        // String grape = input.nextLine();
        // System.out.println("Ruokaehdotus: ");
        // String food = input.nextLine();     
        wineService.createWine(year, country, name);
    }

    private List<Wine> getWines() {
        return wineService.getWines();
    }

    /*
    *Viinin poistaminen
     */
    private void delete() throws Exception {
        System.out.print("Poistettava viini: ");
        String name = input.nextLine();
        wineService.delete(name);
    }

    /*
    *Viinin muokkaaminen
     */
    private void updateWine() throws Exception {
        System.out.println();
        printWines();
        System.out.println();
        System.out.print("Muokattavan viinin id:");
        int id = Integer.parseInt(input.nextLine());
        int realIndex = id - 1;
        System.out.print("Viinin vuosi: ");
        int year = Integer.parseInt(input.nextLine());
        System.out.print("Viinin tuotantomaa: ");
        String country = input.nextLine();
        System.out.print("Viinin nimi: ");
        String name = input.nextLine();
        //System.out.println("Viinin rypälelaji: ");
        //String grape = input.nextLine();
        //System.out.println("Ruokaehdotus: ");
        //String food = input.nextLine();

        wineService.updateWine(realIndex, year, country, name);
        System.out.println("");
        System.out.println("Päivitetty viini");
        printWineById(realIndex);

    }
}
