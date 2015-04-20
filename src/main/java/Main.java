import Engine.Engine;

/**
 * Created by popka on 20.04.15.
 */
public class Main {

    public static void main(String args[ ]) {

        Engine engine = new Engine();

        int result = 0;
        double utils = 0;
        for (int j = 0; j < 300; j++) {
            engine.start();
            result += engine.getUnserviced();
            utils += engine.getUtils();
        }
        System.out.println("unserviced=" + result / 300 + "  utils=" + utils/300);


        //engine.generateTransact();


    }
}
