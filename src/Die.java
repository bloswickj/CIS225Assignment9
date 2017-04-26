/**
 * Created by John on 4/25/2017.
 */

import java.util.Random;

public class Die {

    public int rollDie(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
