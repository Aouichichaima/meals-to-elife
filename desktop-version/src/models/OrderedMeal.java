package models;

public class OrderedMeal {

    private String titre;
    private double unitPrice;
    private int quantity;

    public OrderedMeal() {
    }

    public OrderedMeal(String titre, double unitPrice, int quantity) {
        this.titre = titre;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
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
        return "OrderedMeal {quantity:" + quantity + ", titre:" + titre + ", unitPrice:" + unitPrice + "}";
    }

    
    
}
