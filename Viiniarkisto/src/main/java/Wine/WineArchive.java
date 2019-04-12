/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine;

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
        inputs.put("3", "3 poista viini");
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
                 List<Wine>wineList; 
              wineList = getWines();
           
                for (int i = 0; i < wineList.size(); i++) {
                    System.out.println("Viini:"+wineList.get(i).getName());
                     System.out.println("Vuosi"+wineList.get(i).getYear());
                      System.out.println("Tuotantomaa"+wineList.get(i).getCountry());
                      System.out.println("");
                    
                }
                
                
                
            }else if (komento.equals("3")) {
                deleteWine();

        }
    }
 }
      

    public void printInstruction() {
        ArrayList<Wine>winelist = new ArrayList();
  
        System.out.println();
        for (String komento : this.inputs.values()) {
            System.out.println(komento);
                      

           
        }
   //       System.out.println( inputs.values());
       
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

    private List<Wine> getWines() {
   
   
        System.out.println("tulostus winearchive"+ wineService.getWines());
        return wineService.getWines();
        
    }

    private void deleteWine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
