package Utils;

import java.util.Random;

/**
 * Created by popka on 20.04.15.
 */
public class RandomGenerator {
    Random random = new Random();


    public double randomExp(double lambda) {
        double doubleNumber = random.nextDouble();
        return Math.log(1-doubleNumber)/(-lambda);
    }

}
