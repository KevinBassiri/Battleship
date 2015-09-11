/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.*;

public class Game {
    
    //Declare and initialalize variables we will need
    private GameHelper helper = new GameHelper();
    //Create an ArrayList of SwimpleOpponent opponents, will only hold SimpleOpponent objects
    private ArrayList<SimpleOpponent> opponentList = new ArrayList<>();
    private int numOfGuesses = 0;
    
    private void setUpGame(){
        //Creates 3 enemies with their own names, and puts them in the opponentList ArrayList
        SimpleOpponent enemy1 = new SimpleOpponent();
        enemy1.setName("SS Cheese Wafer ");
        SimpleOpponent enemy2 = new SimpleOpponent();
        enemy2.setName("Admiral's Brigade ");
        SimpleOpponent enemy3 = new SimpleOpponent();
        enemy3.setName("Uncompilable name ");
        opponentList.add(enemy1);
        opponentList.add(enemy2);
        opponentList.add(enemy3);
        
        //Prints instructions for the user
        System.out.println("Sink 3 ships in the fewest number of guesses.");
        
        for(SimpleOpponent opponentToSet : opponentList){ //Repeat with each opponent in the list
            ArrayList<String> newLocation = helper.placeOpponents(3); //Ask the helper for an enemy location
            opponentToSet.setLocationCells(newLocation); //Call the setter method on this enemy,
                                                         //to give it the location you got from helper
        }
    }
        private void startPlaying(){
            while(!opponentList.isEmpty()){ //As long as the opponentList is NOT empty
                String userGuess = helper.getUserInput("Enter a guess"); //Gets user input
                checkUserGuess(userGuess); //Calls our own checkUserGuess method
            }
            finishGame(); //Calls our own finishGame() method
        }
        private void checkUserGuess(String userGuess){
            numOfGuesses++;//Increments the number of guesses the user has made
            String result = "miss"; //Assume it's a miss unless told otherwise
            
            for(SimpleOpponent opponentToTest : opponentList){ //Repeat with all the opponents in the list
                result = opponentToTest.checkYourself(userGuess);
                if (result.equals("hit")){
                //Get out of the loop early, no point in testing others
                break;
                }
                if(result.equals("kill")){
                    //If the opponent is dead remove them from the arraylist and the loop
                    opponentList.remove(opponentToTest);
                    break;
                }
            }
            if (result.equals("hit")){
            System.out.println(result); //Prints the result for the user, only if it is hit
            }
        }
        private void finishGame(){
            //Prints messages telling the user how well they did during the game
            System.out.println("All enemies are dead");
            if (numOfGuesses <= 18){
                System.out.println("It took you " + numOfGuesses + " guesses.");
            }else{
                System.out.println("You sank your opponents in " + numOfGuesses + " guesses, but\n were eaten by seagulls.");
            }
        }
    public static void main(String[] args)
    {
        Game game = new Game(); //Creates the game object
        game.setUpGame(); //Tell the game object to set up the game
        game.startPlaying(); //Tell the game object to start the main gameplay loop
    }
}

