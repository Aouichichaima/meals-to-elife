package models;

public class OrderedMeal {

    private String name;
    private double unitPrice;
    private int quantity;

    public OrderedMeal() {
    }

    public OrderedMeal(String titre, double unitPrice, int quantity) {
        this.name = titre;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderedMeal {quantity:" + quantity + ", name:" + name + ", unitPrice:" + unitPrice + "}";
    }

    
    
}
