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
public class Material {

    int id;
    String name;
    String description;
    int qty;
    double price;
    int partNumber;
    int stock;

    public Material(int id, String name, double price, int qty, int partNumber) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
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
