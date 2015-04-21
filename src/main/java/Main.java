import Engine.Engine;

/**
 * Created by popka on 20.04.15.
 */
public class Main {

    public static void main(String args[ ]) {

        Engine engine = new Engine();

        int countUnserviced = 0;
        double utils = 0;
        int DAYS = 300;
        for (int j = 0; j < DAYS; j++) {
            engine.start();
            countUnserviced += engine.getUnserviced();
            utils += engine.getUtils();
        }
        System.out.println("unserviced=" + countUnserviced / DAYS + "  utils=" + utils/DAYS);


        //engine.generateTransact();


    }
}
