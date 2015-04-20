package Engine.Cars;

/**
 * Created by popka on 20.04.15.
 */
public class PolisherCar extends Car {

    public PolisherCar(double time) {
        super(time, CAR_TO_POLISHER);
    }

    public int getType() {
        return CAR_TO_POLISHER;
    }
}
