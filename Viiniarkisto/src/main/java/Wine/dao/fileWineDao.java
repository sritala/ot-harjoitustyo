/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine.dao;

import Wine.domain.Wine;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author susanna
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
                Wine wine = new Wine(year, country, name, adder);
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

    @Override
    public Wine create(Wine wine) throws Exception {
        System.out.println("FileWineDao " + wine.getAdder());
        wine.setId(generateId());
        this.wines.add(wine);
        save();
        return wine;
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Wine wine : this.wines) {
//                System.out.println("Wine to be saved (name) " + wine.getName() + " // (adder) " + wine.getAdder());
                writer.write(wine.getId() + ";" + wine.getYear() + ";" + wine.getCountry() + ";" + wine.getName() + ";" + wine.getAdder() + "\n");
            }
        }
    }

    @Override
    public List<Wine> getAll() {
        return this.wines;
    }

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

    @Override
    public void updateWine(int id, int year, String country, String name) throws Exception {
        Wine wine = this.wines.get(id);
        wine.setYear(year);
        wine.setCountry(country);
        wine.setName(name);
        this.wines.set(id, wine);
        save();

    }
}
