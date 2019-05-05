package wine.dao;

import wine.domain.Wine;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * WineDao's class, which handles creating and saving of the wines 
 */
public class FileWineDao implements WineDao {

    public List<Wine> wines;
    private String file;

    public FileWineDao(String file) throws Exception {
        this.wines = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String country = parts[2];
                String name = parts[3];
                String adder = parts[4];
                String grape = parts[5];
                String food = parts[6];
                Wine wine = new Wine(year, country, name, adder, grape, food);
                wine.setId(generateId());
                this.wines.add(wine);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    private int generateId() {
        return this.wines.size() + 1;
    }

    /**
     * creates a wine to the list
     *
     * @param wine new created wine
     * @return returns wine for the further handling
     * @throws java.lang.Exception
     */

    @Override
    public Wine create(Wine wine) throws Exception {
        wine.setId(generateId());
        this.wines.add(wine);
        save();
        return wine;
    }

    /**
     * saves wine for the file
     *
     */
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Wine wine : this.wines) {

                writer.write(wine.getId() + ";" + wine.getYear() + ";" + wine.getCountry() + ";" + wine.getName() + ";" + wine.getAdder() + ";" + wine.getGrape() + ";" + wine.getFood() + "\n");
            }
        }
    }

    /**
     * fetches existing wines
     *
     */
    @Override
    public List<Wine> getAll() {
        return this.wines;
    }

    /**
     * deletes wine from file
     *
     * @param name wine to be deleted
     */
    @Override
    public void delete(String name) throws Exception {
        for (int i = 0; i < this.wines.size(); i++) {
            Wine wine = this.wines.get(i);
            if (wine.getName().equalsIgnoreCase(name)) {
                this.wines.remove(i);
            }
            save();
        }
    }

    /**
     * Updates wine and saves the result to file
     *
     * @param id id of the wine
     * @param year new year of the wine
     * @param country new country of the wine
     * @param name new name of the wine
     * @param grape new grape of the wine
     * @param food new food of the wine
     */
    @Override
    public void updateWine(int id, int year, String country, String name, String grape, String food) throws Exception {
        Wine wine = this.wines.get(id);
        wine.setYear(year);
        wine.setCountry(country);
        wine.setName(name);
        wine.setGrape(grape);
        wine.setFood(food);
        this.wines.set(id, wine);
        save();

    }
}

