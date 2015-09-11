/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.io.*;
import java.util.*;
/**
 *
 * @author Kevin
 */
public class GameHelper {

  private static final String alphabet = "abcdefg";
  private int gridLength = 7;
  private int gridSize = 49;
  private int [] grid = new int[gridSize];
  private int comCount = 0;


  public String getUserInput(String prompt) {
     String inputLine = null;
     System.out.print(prompt + "  ");
     try {
       BufferedReader is = new BufferedReader(
	 new InputStreamReader(System.in));
       inputLine = is.readLine();
       if (inputLine.length() == 0 )  return null; 
     } catch (IOException e) {
       System.out.println("IOException: " + e);
     }
     return inputLine.toLowerCase();
  }

  
  
 public ArrayList<String> placeOpponents(int comSize) {                 // line 19
    ArrayList<String> alphaCells = new ArrayList<String>();
    String [] alphacoords = new String [comSize];      // holds 'f6' type coords, not sure what this is used for
    String temp = null;                                //Temporary string for concat
    int [] coords = new int[comSize];                  //Default coordinates
    int attempts = 0;                                  //Default attempts is 0
    boolean success = false;                           //Default successfully found location is false
    int location = 0;                                  //The location to start at when creating our positions
    
    comCount++;                                        //By default comCount is 0, but now it is incremented by 1
    int incr = 1;                                      //The horizontal increment
    if ((comCount % 2) == 1) {                         // if odd dot com  (place vertically)
      incr = gridLength;                               //The vertical increment
    }

    while ( !success & attempts++ < 200 ) {             // main search loop  (32)
	location = (int) (Math.random() * gridSize);      // get random starting point
        System.out.print(" try " + location);
	int x = 0;                                        // nth position in dotcom to place
        success = true;                                 // assume success
        while (success && x < comSize) {                // look for adjacent unused spots
          if (grid[location] == 0) {                    // if not already used
             coords[x++] = location;                    // save location
             location += incr;                          // try 'next' adjacent
             if (location >= gridSize){                 // out of bounds - 'bottom'
               success = false;                         // failure
             }
             if (x>0 & (location % gridLength == 0)) {  // out of bounds - right edge
               success = false;                         // failure
             }
          } else {                                      // found already used location
              System.out.print(" used " + location);  
              success = false;                          // failure
          }
        }
    }                                                   // end while

    int x = 0;                                          // turn good location into alpha coords
    int row = 0;
    int column = 0;
    System.out.println("\n");
    while (x < comSize) {
      grid[coords[x]] = 1;                              // mark master grid pts. as 'used'
      row = (int) (coords[x] / gridLength);             // get row value
      column = coords[x] % gridLength;                  // get numeric column value
      temp = String.valueOf(alphabet.charAt(column));   // convert to alpha
      
      alphaCells.add(temp.concat(Integer.toString(row)));
      x++;

      System.out.print("  coord "+x+" = " + alphaCells.get(x-1)); //Displays coordinates of the ships
      
    }
    System.out.println("\n");
    
    return alphaCells;
   }
}

