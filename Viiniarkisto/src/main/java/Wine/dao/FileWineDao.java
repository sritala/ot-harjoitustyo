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
                Wine wine = new Wine(id, year, country, name);
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
        wine.setId(generateId());
        this.wines.add(wine);
        save();
        return wine;
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Wine wine : this.wines) {
                writer.write(wine.getId() + ";" + wine.getYear()+ ";" + wine.getCountry()+ ";" + wine.getName() + "\n");
            }
        }
    }

    @Override
    public List<Wine> getAll() {
        return this.wines;
    }

    @Override
    public void delete() {
     
    }

}
