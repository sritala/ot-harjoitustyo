/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine.domain;

/**
 *
 * @author susanna
 */
public class Wine {

    private int id;
    private int year;
    private String country;
    private String name;
    private String adder;
//    private Brewery brewery;

//    public Wine(int id, int prodYear, String prodCountry, String wineName) {
//        this.id = id;
//        this.year = prodYear;
//        this.country = prodCountry;
//        this.name = wineName;
//    }
    public Wine(int prodYear, String prodCountry, String wineName, String adder) {
        this.year = prodYear;
        this.country = prodCountry;
        this.name = wineName;
        this.adder = adder;
    }
    
    public String getAdder(){
        return this.adder;
    }

    public int getYear() {
        return this.year;
    }
    public void setAdder(String param){
        this.adder = param;
        
    }

    public void setId(int param) {
        this.id = param;
    }

    public void setName(String param) {
        this.name = param;
    }

    public void setCountry(String param) {
        this.country = param;
    }

    public void setYear(int param) {
        this.year = param;
    }

    public int getId() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    public String getName() {
        return this.name;
    }

}
