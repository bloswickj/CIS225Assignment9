/**
 * Created by John on 4/25/2017.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class WinnersKeeper {

    ArrayList<String> winners;
    File winnerFile;

    public WinnersKeeper(){
        winners = new ArrayList<String>(Arrays.asList("","","","",""));
        winnerFile = new File("winnerFile.csv");
        populateWinners();
    }

    public void printWinners(){
        populateWinners();
        if(!winners.isEmpty()){
            System.out.print("\n\nThe most recent winners are: \n");
            for(String winner : winners){
                System.out.print("\n\t" + winner);
            }
        }
        else{
            System.out.print("\n\nThe most recent winners are not available.");
        }
    }

    public void appendWinnersFile(String winner){
       try {
           FileWriter fw = new FileWriter(winnerFile.getAbsoluteFile(), true);
           BufferedWriter bw =  new BufferedWriter(fw);
           bw.write("," + winner);
           bw.flush();
           bw.close();
       }
       catch(java.io.IOException exception){
            System.out.print("\n\nError saving winner to: \"" + winnerFile + "\"");
       }
    }

    private void populateWinners(){
        try{
            int counter = 0;
            Scanner scan = new Scanner(winnerFile);
            String[] winnersArray = scan.nextLine().split(",");
            for(int i = winnersArray.length - 1; i > winnersArray.length - 6; i--){
                if(i >= 0) {
                    winners.set(counter, winnersArray[i]);
                    counter++;
                }
                else{
                    break;
                }
            }

        }
        catch(java.io.FileNotFoundException exception){
            System.out.print("\n\nError loading: \"" + winnerFile + "\"");
        }
    }
}
