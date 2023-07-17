/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author HIEU
 */
public class Car implements Comparable{
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return "<" + carID + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + "- " + brand.getPrice() + ", " + color + ", " + frameID + ", " + engineID + ">";
    }

    @Override
    public int compareTo(Object o) {
        Car c = (Car)o;
        if (this.brand.getBrandName().compareTo(c.brand.getBrandName()) > 0 && this.color.compareTo(c.color) < 0) return -1;
        else return 1;
    }    
        
}
