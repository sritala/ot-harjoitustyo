package wine.ui;

import wine.dao.FileUserDao;
import wine.domain.WineService;
import wine.dao.FileWineDao;
import wine.domain.Wine;
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
 * Represents the user interface
 */
public class Ui {

    private Scanner input;
    private Map<String, String> inputs;
    private Map<String, String> setup;
    private WineService wineService;

    public Ui(Scanner input) throws FileNotFoundException, IOException, Exception {
        this.input = input;
        inputs = new TreeMap<>();
        setup = new TreeMap<>();

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
     * Shown if the log in is successfull
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
     * Run if setup is successfull
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
     * Listing the wines
     *
     * @param year production year of the wine
     * @param country production country of the wine
     * @param name name of the wine
     * @param grape grape of the wine
     * @param food food compliment of the wine
     */
    public void printWine(Wine wine) {
        System.out.println("Id: " + wine.getId());
        System.out.println("Nimi: " + wine.getName());
        System.out.println("Rypälelaji: " + wine.getGrape());
        System.out.println("Valmistusvuosi: " + wine.getYear());
        System.out.println("Tuotantomaa: " + wine.getCountry());
        System.out.println("Ruokaehdotus: " + wine.getFood());
        System.out.println();
    }

    /*
    *Listing wines by index
     */
    public void printWineById(int index) {
        List<Wine> wineList;
        wineList = getWines();
        Wine printableWine = wineList.get(index);
        printWine(printableWine);
    }

    /**
     * Adding wine to logged in user
     *
     * @param year production year of the wine
     * @param country production country of the wine
     * @param name name of the wine
     * @param grape grape of the wine
     * @param food food compliment of the wine
     */
    public void addWine() {
        try {
            System.out.print("Viinin vuosi: ");
            int year = Integer.parseInt(input.nextLine());
            System.out.print("Viinin tuotantomaa: ");
            String country = input.nextLine();
            System.out.print("Viinin nimi: ");
            String name = input.nextLine();
            System.out.print("Viinin rypälelaji: ");
            String grape = input.nextLine();
            System.out.print("Ruokaehdotus: ");
            String food = input.nextLine();
            wineService.createWine(year, country, name, grape, food);
        } catch (Exception e) {
            System.out.println("Kirjoita vuosi numeroina");
        }

    }

    private List<Wine> getWines() {
        return wineService.getWines();
    }

    /*
    *Removes the wine
     */
    private void delete() throws Exception {
        System.out.print("Poistettava viini: ");
        String name = input.nextLine();
        wineService.delete(name);
    }

    /*
    *Modifying of the wine 
     */
    private void updateWine() {
        try {
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
            System.out.print("Viinin rypälelaji: ");
            String grape = input.nextLine();
            System.out.print("Ruokaehdotus: ");
            String food = input.nextLine();

            wineService.updateWine(realIndex, year, country, name, grape, food);
            System.out.println("");
            System.out.println("Päivitetty viini");
            printWineById(realIndex);
        } catch (Exception e) {
            System.out.println("Kirjoita vuosi numeroina");
        }

    }
}
