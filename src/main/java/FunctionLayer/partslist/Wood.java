/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.partslist;

/**
 *
 * @author Dan
 */
public class Wood {

    int id;
    String name;
    String description;
    double price;
    double length;
    double width;
    double height;
    int qty;
    int amountOfScrews;
    int partNumber;
    int stock;

    public Wood(int id, String name, double price, double height, double width, double length, int qty, int partNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
        this.qty = qty; 
        this.partNumber = partNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAmountOfScrews() {
        return amountOfScrews;
    }

    public void setAmountOfScrews(int amountOfScrews) {
        this.amountOfScrews = amountOfScrews;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }
    
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
