/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine.domain;

import Wine.dao.WineDao;
import Wine.domain.Wine;
import java.util.List;

/**
 *
 * @author susanna
 */
public class WineService {
    private WineDao wineDao;
    
    public WineService(WineDao wineDao) {
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
    
}
