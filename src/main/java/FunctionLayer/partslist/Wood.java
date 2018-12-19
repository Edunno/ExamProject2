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

    /**
     * Returns the id of the wood
     *
     * @return The id as int
     */
    public int getId() {
        return id;
    }

    /**
     * Returs the name of the wood
     *
     * @return the name as String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the wood
     *
     * @return the description as String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the wood.
     *
     * This description is displayed in the partslist
     *
     * @param description described in a String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of the wood as a double.
     *
     * @return price as double
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the length of the wood in cm
     * 
     * @return double in centimeters
     */

    public double getLength() {
        return length;
    }
    
    /**
     * Returns the width of the wood in mm
     * 
     * @return double in millimeters
     */

    public double getWidth() {
        return width;
    }
    
    /**
     * Returns the height of the wodo in mm
     * 
     * @return double in millimeters
     */

    public double getHeight() {
        return height;
    }
    
    /**
     * Returns the quantity of wood.
     * 
     * @return quantity as int
     */

    public int getQty() {
        return qty;
    }
    
    /**
     * Sets the quantity of wood needed.
     * 
     * This number is shown on the partslist and used to calculate the price
     * 
     * @param qty int describing amount of wood
     */

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    /**
     * Returns the amount of screws needed for the wood
     * 
     * @return amount as int
     */

    public int getAmountOfScrews() {
        return amountOfScrews;
    }
    
    /**
     * Sets the amount of screws needed for the wood
     * 
     * @param amountOfScrews amount of screws needed for the specific piece of wood
     */

    public void setAmountOfScrews(int amountOfScrews) {
        this.amountOfScrews = amountOfScrews;
    }
    
    /**
     * Returns the part number of the wood.
     * 
     *  The partnumber is used for identifying what the wood is used for
     * 
     * @return int used for identifying the wood
     */

    public int getPartNumber() {
        return partNumber;
    }
    
    /**
     * Sets the partnumber of the wood.
     * 
     * 
     * 
     * @param partNumber is used for identifying what the wood is used for
     */

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Returns the stock quantity of the wood
     * 
     * @return stock as int
     */
    
    public int getStock() {
        return stock;
    }
    
    /**
     * Sets the stock quantity of the wood.
     * 
     * @param stock amount of a specific wood in stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

}
