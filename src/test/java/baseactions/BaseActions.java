package baseactions;

import java.util.Random;

public class BaseActions {
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
