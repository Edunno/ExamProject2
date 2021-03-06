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

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param qty
     * @param partNumber
     */
    public Material(int id, String name, double price, int qty, int partNumber) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.partNumber = partNumber;
    }
    
    /**
     * Returns the id of the material
     * 
     * @return id as int
     */

    public int getId() {
        return id;
    }
    
    /**
     * Returs the name of the material
     * 
     * @return name as String
     */

    public String getName() {
        return name;
    }
    
    /**
     * Returns the description of the material
     * 
     * @return description as String
     */

    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description for the material.
     * 
     *This description is displayed in the partslist
     * 
     * @param description A String describing the material
     */

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Returns the quantity of the material
     * 
     * @return amount in int
     */

    public int getQty() {
        return qty;
    }

    /**
     * Sets the quantity for the material.
     * 
     * This quantity is displayed in the partslist
     * 
     * @param qty set amount of the material
     */
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    /**
     * Returns the price of the material as a double.
     * 
     * @return price as double
     */

    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the partnumber of the material.
     * 
     * The partnumber is used for identifying what materials are used for
     *
     * 
     * @return the partnumber as an int
     */

    public int getPartNumber() {
        return partNumber;
    }
    
    /**
     * Sets the partnumber of the material.
     * 
     *  The partnumber is used for identifying what materials are used for
     * @param partNumber as int
     */

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }
    
    /**
     * Returns the stock of the material object
     * 
     * @return stock as int
     */

    public int getStock() {
        return stock;
    }
    
    /**
     * Sets the stock on the material object
     * 
     * @param stock amount in stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

}
