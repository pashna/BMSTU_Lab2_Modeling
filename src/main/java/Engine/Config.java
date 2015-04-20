package Engine;

/**
 * Created by popka on 21.04.15.
 */
public class Config {
    public final static double MAX_TIME = 480;
    public final static int WORKERS_COUNT = 3;

    public final static int MAIN_PARKING_COUNT = 3;
    public final static int WASHING_PARKING_COUNT = 2;

    public final static double LAMBDA_ARRIVE_POLISHING = (double)1/30;
    public final static double LAMBDA_ARRIVE_WASHING = (double)1/5;

    public final static double LAMBDA_POLISHING = (double)1/15;
    public final static double LAMBDA_WASHING = (double)1/4;


}
