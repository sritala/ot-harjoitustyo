/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine;

/**
 *
 * @author susanna
 */
public class Wine {
    
    private int id;
    private int year;
    private String country;
    private String name;
//    private Brewery brewery;

    public Wine(int id, int prodYear, String prodCountry, String wineName) {
        this.id = id;
        this.year = prodYear;
        this.country = prodCountry;
        this.name = wineName;
    }
    
    public Wine(int prodYear, String prodCountry, String wineName) {
        this.year = prodYear;
        this.country = prodCountry;
        this.name = wineName;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setId(int param){
        this.id = param;
    }

    public int getId() {
        return this.id;
    }
    
    public String getCountry(){
        return this.country;
    }

    public String getName(){
        return this.name;
    }

}
