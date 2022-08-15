package models;

public class Restaurant {

    private int id;  
    private String name; 
    private String address; 
    private String menu;
    private double rating; 
    private int id_manager;

    

    public Restaurant(int id, String name, String address, String menu, double rating, int id_manager) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.rating = rating;
        this.id_manager = id_manager;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int getId_manager() {
        return id_manager;
    }
    public void setId_manager(int id_manager) {
        this.id_manager = id_manager;
    }

    
    
}
