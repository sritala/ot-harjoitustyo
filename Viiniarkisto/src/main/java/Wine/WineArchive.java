/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author susanna
 */
public class WineArchive {

    private Scanner input;
    private Map<String, String> inputs;
    private WineService wineService;

    public WineArchive(Scanner input) throws FileNotFoundException, IOException, Exception {
        this.input = input;
        inputs = new TreeMap<>();
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));

//        String wineFile = properties.getProperty("wineFile");
        FileWineDao wineDao = new FileWineDao("wines.txt");
        wineService = new WineService(wineDao);

        inputs.put("x", "x lopeta");
        inputs.put("1", "1 lisää viini");
        inputs.put("2", "2 listaa viinit");
    }

    public void start() {
        System.out.println("Viiniarkisto");
        while (true) {
            printInstruction();
            System.out.println();
            System.out.print("komento: ");
            String komento = input.nextLine();
            if (!inputs.keySet().contains(komento)) {
                System.out.println("virheellinen komento.");
            }

            if (komento.equals("x")) {
                break;
            } else if (komento.equals("1")) {
                addWine();
            } else if (komento.equals("2")) {
                getWines();
            }

        }
    }

    public void printInstruction() {
        System.out.println("Ohje");
        System.out.println();
        for (String komento : this.inputs.values()) {
            System.out.println(komento);
        }
    }

    public void addWine() {
        System.out.print("Viinin vuosi: ");
        int year = Integer.parseInt(input.nextLine());
        System.out.print("Viinin tuotantomaa: ");
        String country = input.nextLine();
        System.out.print("Viinin nimi: ");
        String name = input.nextLine();
        wineService.createWine(year, country, name);
    }

    private void getWines() {
        System.out.println(wineService.getWines());
    }
}
