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
import java.util.Scanner;
import java.util.StringTokenizer;
import tool.Inputter;
import ui.Menu;

/**
 *
 * @author HIEU
 */
public class BrandList extends ArrayList<Brand> {

    Scanner sc = new Scanner(System.in);
    
    public BrandList() {
    }
    
    public void loadFromFile(String fName) {
        try {
            File f = new File(fName);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                // Spit the readLine into parts
                StringTokenizer stk = new StringTokenizer(line, ",:");
                String id = stk.nextToken().trim();
                String brandName = stk.nextToken().trim();
                String soundBrand = stk.nextToken().trim();
                double price = Double.parseDouble(stk.nextToken());
                
                // Create a brand from the input data
                Brand b = new Brand(id, brandName, soundBrand, price);
                this.add(b);
            }
            // Close the file
            bf.close(); fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fName) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Brand x : this) {
                pw.println(x);
            }
            // Clost the file
            pw.close(); fw.close();
            System.out.println("All data has been saved!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(bID)) return i;
        }
        return -1;
    }
    
    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand)mnu.ref_getChoice(this);
    }
    
    public void addBrand() {
        String id = Inputter.inputNonBlankStr("-Enter an ID");
        if (searchID(id) != -1) {
            System.out.println("This ID has already existed.");
            return;
        }
        String brandName = Inputter.inputNonBlankStr("-Enter a brand name");
        String soundName = Inputter.inputNonBlankStr("-Enter a sound brand");
        double price = Inputter.inputDouble("-Enter price");
        
        // Create a new brand 
        Brand b = new Brand(id, brandName, soundName, price);
        
        // Add the brand to the list
        this.add(b);
        System.out.println("This new brand has been added.");
    }
    
    public void updateBrand() {
        String id = Inputter.inputNonBlankStr("-Enter an ID");
        int pos = searchID(id);
        if (pos == -1) {
            System.out.println("This ID cannot found!");
            return;
        }
        String brandName = Inputter.inputNonBlankStr("-Update a brand name");
        String soundName = Inputter.inputNonBlankStr("-Update a sound brand");
        double price = Inputter.inputDouble("-Update price");
        
        this.get(pos).setBrandName(brandName);
        this.get(pos).setSoundBrand(soundName);
        this.get(pos).setPrice(price);
        
        System.out.println("All the information has been updated.");
    }
    
    public void listBrands() {
        for (Brand x : this) {
            System.out.println(x);
        }
    }
}
