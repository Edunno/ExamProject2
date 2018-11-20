/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partslist;

/**
 *
 * @author Dan
 */
public class Wood {

    String description;
    double price;
    double length;
    double width;
    double height;

    public Wood(String description, double price, double length, double width, double height) {
        this.description = description;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getDescription() {
        return description;
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
    
    

    
     
   
}
