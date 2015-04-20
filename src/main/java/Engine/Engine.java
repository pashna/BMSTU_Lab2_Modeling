package Engine;

import Engine.Cars.Car;
import Engine.Cars.PolisherCar;
import Engine.Cars.WashingCar;
import Engine.Workers.Workers;
import Utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Collections;

import static Engine.Config.*;

/**
 * Created by popka on 20.04.15.
 */
public class Engine {

    private RandomGenerator random = new RandomGenerator();
    private Parking mainParking;
    private Parking washingParking;
    private Workers workers;


    ArrayList<Car> cars;

    public Engine() {
    }

    public void start() {

        mainParking = new Parking(MAIN_PARKING_COUNT);
        workers = new Workers(WORKERS_COUNT);
        washingParking = new Parking(WASHING_PARKING_COUNT);
        generateTransact();

        int generate=0;
        int terminate=0;
        while (cars.size() > 0) {

            Car currentCar = cars.get(0);
            double time = cars.get(0).getTime();
            switch (cars.get(0).getState()) {


                case Car.NOT_EXIST:
                    currentCar.setState(Car.ARRIVED);
                    generate++;
                    break;



                case Car.ARRIVED:

                    if (mainParking.isFull()) {
                        mainParking.incrementUnserviced();
                        cars.remove(0);
                    }
                    else {
                        mainParking.add(currentCar);
                        currentCar.setState(Car.MAIN_PARKING);
                    }
                    break;



                case Car.MAIN_PARKING:

                    if (currentCar.getType() == Car.CAR_TO_WASHING)
                        if (!washingParking.isFull()) {
                            mainParking.removeFirst();
                            currentCar.setState(Car.WASHING_PARKING);
                            washingParking.add(currentCar);
                        } else {
                            currentCar.setTime(workers.getNearFreeTime(1));
                        }

                    if (currentCar.getType() == Car.CAR_TO_POLISHER) {
                        if (workers.isFree(time, 2)) {
                            mainParking.removeFirst();
                            double advance = random.randomExp(LAMBDA_POLISHING);
                            workers.enter(2, time, advance);
                            currentCar.setState(Car.IN_PROCESS);
                            currentCar.setTime(time + advance);
                        } else {
                            currentCar.setTime(workers.getNearFreeTime(2));
                        }
                    }
                    break;



                case Car.WASHING_PARKING:
                    if (workers.isFree(time, 1)) {
                        washingParking.removeFirst();
                        double advance = random.randomExp(LAMBDA_WASHING);
                        workers.enter(1, time, advance);
                        currentCar.setState(Car.IN_PROCESS);
                        currentCar.setTime(time + advance);
                    } else {
                        currentCar.setTime(workers.getNearFreeTime(1));
                    }
                    break;



                case Car.IN_PROCESS:
                    cars.remove(currentCar);
                    terminate++;
                    break;

            }

            Collections.sort(cars);


        }

        //System.out.println("gen = " + generate + "  ter" + terminate);
    }

    public void generateTransact() {
        cars = new ArrayList<Car>();

        double time = 0;
        while (time < MAX_TIME) {
            time += random.randomExp(LAMBDA_ARRIVE_WASHING);
            if (time < MAX_TIME)
                cars.add(new WashingCar(time));
        }

        time = 0;
        while (time < MAX_TIME) {
            time += random.randomExp(LAMBDA_ARRIVE_POLISHING);
            if (time < MAX_TIME)
                cars.add(new PolisherCar(time));
        }

        Collections.sort(cars);

    }

    public int getUnserviced() {
        return mainParking.getUnserviced();
    }

    public double getUtils() {
        return workers.getUtils()/(workers.getCountOfWorkers()*MAX_TIME);
    }


}
