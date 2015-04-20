package Utils;

import java.util.Random;

/**
 * Created by popka on 20.04.15.
 */
public class RandomGenerator {
    Random random;
    double lambda;

    public RandomGenerator(double lambda) {
        this.lambda = lambda;
        random = new Random();
    }

    public double nextExp() {
        double doubleNumber = random.nextDouble();

        return Math.log(1-doubleNumber)/(-lambda);
    }

}
