/**
 * Created by John on 4/25/2017.
 */

import java.util.ArrayList;

public class DiceController {

    private ArrayList<Die> dice;

    public DiceController(){
        dice = new ArrayList<Die>();
        populateDice();
    }

    public ArrayList<Integer> rollDice(){
        ArrayList<Integer> rolls = new ArrayList<Integer>();

        for(Die die : dice){
            rolls.add(die.rollDie());
        }
        return rolls;
    }

    public boolean checkRollsForOnes(ArrayList<Integer> rolls){
        for(Integer roll : rolls){
            if(roll == 1){
                return true;
            }
        }
        return false;
    }

    public void printRolls(ArrayList<Integer> rolls){
        System.out.print("\nRolls: ");
        for(Integer roll : rolls){
            System.out.print(" " + roll);
        }
    }

    private void populateDice(){
        for(int i = 0; i < 3; i++){
            dice.add(new Die());
        }
    }
}
