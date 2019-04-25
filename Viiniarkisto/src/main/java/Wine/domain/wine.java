package Wine.domain;

/**
 * Yksittäistä viiniä kuvaava luokka
 */
public class Wine {

    private int id;
    private int year;
    private String country;
    private String name;
    private String adder;
    //private String grape;
    //private String food;

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
        //this.grape = grape;
        //this.food = food;

    }

    public String getAdder() {
        return this.adder;
    }

    public int getYear() {
        return this.year;
    }

    public void setAdder(String param) {
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
    // public void setGrape (int param){
    //this.grape = param;
//} 

    public int getId() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    public String getName() {
        return this.name;
    }
    //public String getGrape(){
    //return this.grape;
//}
    //public String getFood(){
    //return this.food;
//}

}
