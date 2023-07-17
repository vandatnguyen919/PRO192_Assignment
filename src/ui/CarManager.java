/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.BrandList;
import dto.CarList;
import java.util.ArrayList;
import tool.Inputter;

/**
 *
 * @author HIEU
 */
public class CarManager {
    public static void main(String[] args) {
        
        String filename1_1 = "brands-in.txt";
        String filename1_2 = "brands-out.txt";
        String filename2_1 = "cars-in.txt";
        String filename2_2 = "cars-out.txt";
        
        ArrayList<String> ops = new ArrayList<>();
        ops.add("Brand Management");
        ops.add("Car Management");
        ops.add("Quit");
        Menu mnu = new Menu();
        
        BrandList bList = new BrandList();
        bList.loadFromFile(filename1_1);       

        CarList cList = new CarList(bList);
        cList.loadFromFile(filename2_1);
        
        int choice;
        do {
            System.out.println("\n----------------------------");
            System.out.println("    CAR MANAGING PROGRAM    ");
            System.out.println("----------------------------");
            choice = mnu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    ArrayList<String> bOps = new ArrayList<>();
                    bOps.add("List all brands");
                    bOps.add("Add a new brand");
                    bOps.add("Search a brand base on its ID");
                    bOps.add("Update a brand");
                    bOps.add("Save brands to the file, named brands-out.txt");
                    bOps.add("Quit");
                    do {                        
                        System.out.println("\n---- BRAND MANAGEMENT ----");                       
                        choice = mnu.int_getChoice(bOps);
                        switch(choice) {
                            case 1: bList.listBrands(); break;
                            case 2: bList.addBrand(); break;
                            case 3: String id = Inputter.inputNonBlankStr("-Enter an ID");
                                System.out.println((bList.searchID(id) != -1) ? ("Found! " + "\n" + bList.get(bList.searchID(id)) ) : "Not found"); break;
                            case 4: bList.updateBrand(); break;
                            case 5: bList.saveToFile(filename1_2);
                        }
                    } while (choice > 0 && choice < bOps.size());
                    choice = 1; break;
                case 2: 
                   ArrayList<String> cOps = new ArrayList<>();
                   cOps.add("List all cars in descending order of brand names and ascending order of colors");
                   cOps.add("List cars based on a part of an input brand name");
                   cOps.add("Add a new car");
                   cOps.add("Remove a car based on its ID");
                   cOps.add("Update a car based on its ID");
                   cOps.add("Save cars to file, named cars-out.txt");
                   cOps.add("Quit");
                    do {                        
                        System.out.println("\n---- CAR MANAGEMENT ----");
                        choice = mnu.int_getChoice(cOps);
                        switch(choice) {
                            case 1: cList.listCars(); break;
                            case 2: cList.printByBrandName(); break;
                            case 3: cList.addCar(); break;
                            case 4: cList.removeCar(); break;
                            case 5: cList.updateCar(); break;
                            case 6: cList.saveToFile(filename2_2);;
                        }
                    } while (choice > 0 && choice < cOps.size());
                    choice = 2; break;
            }
        } while (choice > 0 && choice <= ops.size() - 1);
    }
}
