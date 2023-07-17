/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HIEU
 * @param <E>
 */
public class Menu<E> {
    public int int_getChoice(ArrayList<E> options) {
        int response;
        int n = options.size();
        for (int i = 0; i < n; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(options.get(i));
        }
        System.out.println("");
        System.out.println("Please choose an option 1.." + n + ": ");
        Scanner sc = new Scanner(System.in);
        response = Integer.parseInt(sc.nextLine()); // Get user's choice
        return response;        
    }
    
    public E ref_getChoice(ArrayList<E> options) {
        int response;
        int n = options.size();
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > n);
        return options.get(response - 1);
    } 
}
