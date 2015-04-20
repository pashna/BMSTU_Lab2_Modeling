import Engine.Engine;
import Utils.RandomGenerator;

/**
 * Created by popka on 20.04.15.
 */
public class Main {

    public static void main(String args[ ]) {
        double lambdaArrivePolisher = (double)1/30;
        double lambdaArriveWashing = (double)1/5;

        Engine engine = new Engine(lambdaArriveWashing, lambdaArrivePolisher);

        engine.start();

        //engine.generateTransact();


    }
}
