/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import tool.Inputter;
import ui.Menu;

/**
 *
 * @author HIEU
 */
public class CarList extends ArrayList<Car>{
    private BrandList bList;

    public CarList(BrandList bList) {
        this.bList = bList;
    }
    
    public void loadFromFile(String fName) {
        try {
            File f = new File(fName);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String carID = stk.nextToken().trim();
                String brandID = stk.nextToken().trim();
                String color = stk.nextToken().trim();
                String frameID = stk.nextToken().trim();
                String engineID = stk.nextToken().trim();
                int pos = bList.searchID(brandID);
                Brand b = bList.get(pos);
                
                //Create a car
                Car c = new Car(carID, b, color, frameID, engineID);
                this.add(c);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fName) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car x : this) {
                pw.println("<" + x.getCarID() + ", " + x.getBrand().getID() + ", " + x.getColor() + ", " + x.getFrameID() + ", " + x.getEngineID() + ">");
            }
            //Close the file
            pw.close(); fw.close();
            System.out.println("All data has been saved successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int searchID(String cID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equals(cID)) return i;
        }
        return -1;
    }
    
    public int searchFID(String fID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(fID)) return i;
        }
        return -1;
    }
    
    public int searchEID(String eID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equals(eID)) return i;
        }
        return -1;
    }
    
    public void addCar() {
        String carID = Inputter.inputNonBlankStr("-Enter an ID");
        if (searchID(carID) != -1) {
            System.out.println("This ID has already existed.");
            return;
        }
        Menu mnu = new Menu();
        Brand b = (Brand)mnu.ref_getChoice(bList);
        String color = Inputter.inputNonBlankStr("-Enter a color");
        String frameID = Inputter.inputNonBlankStr("-Enter frame ID");
        if (frameID.matches("^F\\d{5}$") == false || searchFID(frameID) != -1) {
            System.out.println("This frame ID is invalid!"); 
            return;
        }
        String engineID = Inputter.inputNonBlankStr("-Enter engine ID");
        if (engineID.matches("^E\\d{5}$") == false || searchEID(engineID) != -1) {
            System.out.println("This engine ID is invalid!");
            return;
        }
        
        //Create a car
        Car c = new Car(carID, b, color, frameID, engineID);
        this.add(c);
        System.out.println("A new has been added successfully!");
    }
    
    public void printByBrandName() {
        String aPartOfBrandName = Inputter.inputNonBlankStr("-Enter a part of a brand name");
        int n = this.size();
        int cnt = 0;
        for (Car x : this) {
            if (x.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(x);
                cnt++;
            }
        }
        if (cnt == 0) System.out.println("No car is detected!");
    }
    
    public void removeCar() {
        String id = Inputter.inputNonBlankStr("-Enter an ID");
        if (searchID(id) != -1) {
            this.remove(searchID(id));
            System.out.println("This ID has been removed successfully!");
        } else {
            System.out.println("This ID cannot found!");
        }
    }
    
    public void updateCar() {
        String id = Inputter.inputNonBlankStr("-Enter an ID");
        int pos = searchID(id);
        if (pos == -1) {
            System.out.println("This ID cannot found!");
            return;
        }
        System.out.println(this.get(pos));
        System.out.println("-Update new brand:");
        Menu mnu = new Menu();
        Brand b = (Brand)mnu.ref_getChoice(bList);
        String color = Inputter.inputNonBlankStr("-Update color");
        String frameID = Inputter.inputNonBlankStr("-Update frame ID");
        if (frameID.matches("^F\\d{5}$") == false || searchFID(frameID) != -1) {
            System.out.println("This frame ID is invalid!"); 
            return;
        }
        String engineID = Inputter.inputNonBlankStr("-Enter engine ID");
        if (engineID.matches("^E\\d{5}$") == false || searchEID(engineID) != -1) {
            System.out.println("This engine ID is invalid!");
            return;
        }
        
        this.get(pos).setBrand(b);
        this.get(pos).setColor(color);
        this.get(pos).setFrameID(frameID);
        this.get(pos).setEngineID(engineID);
    }
    
    public void listCars() {
        Collections.sort(this, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) < 0) return 1;
                else if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) == 0) {
                    if (o1.getColor().compareTo(o2.getColor()) > 0) return 1;
                    else return -1;
                }
                else return -1;
            }
        });
        
        for (Car x : this) {
            System.out.println(x);
        }
    }
}
