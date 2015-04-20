package Engine;

import Engine.Workers.Workers;

/**
 * Created by popka on 21.04.15.
 */
public class Result {
    private int unserviced;

    public Result(Workers workers, Parking parking) {
        this.unserviced = parking.getUnserviced();


    }
}
