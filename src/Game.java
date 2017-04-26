/**
 * Created by John on 4/25/2017.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private String player1, player2;
    private int player1Score, player2Score, currentPlayer, winner;
    DiceController diceController;

    public Game() {
        diceController = new DiceController();
        gameLoop();
    }

    private void gameLoop(){
        boolean gameOver = false;
        WinnersKeeper winnersKeeper = new WinnersKeeper();
        while(!gameOver){
            boolean roundOver = false;
            player1Score = 0;
            player2Score = 0;
            currentPlayer = 0;
            winner = 0;
            printWelcome();
            winnersKeeper.printWinners();
            setPlayers();
            while(!roundOver){
                setCurrentPlayer();
                playerRoll();
                printPlayerScores();
                setWinner();
                if(winner != 0){
                    printWinner();
                    saveWinner();
                    roundOver = true;
                }
            }
            if(!checkPlayAgain()){
                printGoodbye();
                gameOver = true;
            }
        }
    }

    private void playerRoll(){
        int totalRoll = 0;
        boolean finished = false;
        Scanner scan = new Scanner(System.in);
        if(currentPlayer == 1) {
            System.out.print("\n\nIt is " + player1 + "'s turn.");
        }
        else{
            System.out.print("\n\nIt is " + player2 + "'s turn.");

        }
        System.out.print("\n\nEnter '1' to roll. Enter '2' to stop. ");
        do{
            String input = scan.next();
            if(input.equals("1")){
                ArrayList<Integer> rolls = new ArrayList<Integer>(diceController.rollDice());
                diceController.printRolls(rolls);
                if(diceController.checkRollsForOnes(rolls)){
                    totalRoll = 0;
                    System.out.print("\n\nOink Oink! \n\nTotal Roll: 0");
                    finished = true;
                }
                else{
                    for(Integer roll : rolls){
                        totalRoll += roll;
                    }
                    System.out.print("\n\nTotal Roll: " + totalRoll + " ");
                }
            }
            else{
                if(currentPlayer == 1) {
                    addPlayer1Score(totalRoll);
                }
                else {
                    addPlayer2Score(totalRoll);
                }
                finished = true;
            }
        }while(!finished);
    }

    private boolean checkPlayAgain(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nWould you like to play again? ");
        String input = scan.next();
        while(!(input.toUpperCase().equals("YES") || input.toUpperCase().equals("Y") || input.toUpperCase().equals("1") || input.toUpperCase().equals("NO") || input.toUpperCase().equals("N") || input.toUpperCase().equals("2"))){
            System.out.print("\nThat is not a valid choice!");
            input = scan.next();
        }
        if(input.toUpperCase().equals("YES") || input.toUpperCase().equals("Y") || input.toUpperCase().equals("1")){
            return true;
        }
        else{
            return false;
        }
    }

    private void setPlayers(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nWhat is player 1's name? ");
        player1 = scan.next();
        System.out.print("\n\nWhat is player 2's name? ");
        player2 = scan.next();
    }

    private void setCurrentPlayer(){
        if(currentPlayer == 0){
            Random rand = new Random();
            currentPlayer = rand.nextInt(2) + 1;
        }
        else{
            if(currentPlayer == 1){
                currentPlayer = 2;
            }
            else{
                currentPlayer = 1;
            }
        }
    }

    private void setWinner(){
        if(currentPlayer == 1){
            if(player1Score >= 100){
                winner = 1;
            }
        }
        else{
            if(player2Score >= 100){
                winner = 2;
            }
        }
    }

    private void printWelcome(){
        System.out.print("\nWelcome to 3 dice PIG!");
    }

    private void printGoodbye(){
        System.out.print("\n\n\tGoodbye...");
    }

    private void printWinner(){
        if(winner == 1){
            System.out.print("\n\n" + player1 + " is the winner!");
        }
        else if(winner == 2){
            System.out.print("\n\n" + player2 + " is the winner!");
        }
    }

    private void printPlayerScores(){
        System.out.print("\n\n" + player1 + "'s score: " + player1Score);
        System.out.print("\n\n" + player2 + "'s score: " + player2Score);
    }

    private void saveWinner(){
        WinnersKeeper winnersKeeper = new WinnersKeeper();
        if(winner == 1){
            winnersKeeper.appendWinnersFile(player1);
        }
        else if(winner == 2){
            winnersKeeper.appendWinnersFile(player2);
        }
    }

    private void addPlayer1Score(int amount){
        player1Score += amount;
    }

    private void addPlayer2Score(int amount){
        player2Score += amount;
    }
}
