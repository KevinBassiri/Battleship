/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.util.ArrayList;
/**
 *
 * @author Kevin
 */
public class SimpleOpponent {
    ArrayList<String> locationCells; //Opponent instance variables, an ArrayList of cell locations
    private String name; //and the name of the opponent
    
    public void setLocationCells(ArrayList<String> locs){ //A setter method that updates
        locationCells = locs;                             //the opponent's location
    }
    public void setName(String n){//Basic setter method
        name = n;
    }
    
    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput); //If the entry is in the list its location will be returned, if not it returns -1
        if (index >= 0){
            locationCells.remove(index); //.remove deletes an entry in the arraylist
        
             if (locationCells.isEmpty()){ //Use the isEmpty method to see if all locations have been guessed
             result = "kill";
             System.out.println("You blew up " + name);
             }else{
            result = "hit";
            }
        }
     return result; //Return "miss" or "hit" or "kill"
    }
}
