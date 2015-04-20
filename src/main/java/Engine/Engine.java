package Engine;

import Engine.Cars.Car;
import Engine.Cars.PolisherCar;
import Engine.Cars.WashingCar;
import Engine.Workers.Workers;
import Utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by popka on 20.04.15.
 */
public class Engine {
    private double lambdaWashing;
    private double lambdaPolisher;
    private RandomGenerator random = new RandomGenerator();
    private RandomGenerator polisherGenerate;
    private Parking parking;
    private Workers workers;


    ArrayList<Car> cars;

    public Engine(double lambdaWashing, double lambdaPolisher) {
        this.lambdaWashing = lambdaWashing;
        this.lambdaPolisher =lambdaPolisher;

    }

    public void start() {

        parking = new Parking(3);
        workers = new Workers(3);
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
                    if (parking.isFull()) {
                        parking.incrementUnserviced();
                        cars.remove(0);
                    }
                    else {
                        parking.add(currentCar);
                        currentCar.setState(Car.MAIN_PARKING);
                    }
                    break;


                case Car.MAIN_PARKING:

                    if (currentCar.getType() == Car.CAR_TO_WASHING)
                        if (workers.isFree(time, 1)) {
                            parking.removeFirst();
                            double advance = random.randomExp((double)1/4);
                            workers.enter(1, time, advance);
                            currentCar.setState(Car.IN_PROCESS);
                            currentCar.setTime(time + advance);
                        } else {
                            currentCar.setTime(workers.getNearFreeTime(1));
                        }

                    if (currentCar.getType() == Car.CAR_TO_POLISHER) {
                        if (workers.isFree(time, 2)) {
                            parking.removeFirst();
                            double advance = random.randomExp((double)1/15);
                            workers.enter(2, time, advance);
                            currentCar.setState(Car.IN_PROCESS);
                            currentCar.setTime(time + advance);
                        } else {
                            currentCar.setTime(workers.getNearFreeTime(2));
                        }
                    }

                    break;
                case Car.WASHING_PARKING:

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
        while (time < 480) {
            time += random.randomExp(lambdaWashing);
            if (time < 480)
                cars.add(new WashingCar(time));
        }

        time = 0;
        while (time < 480) {
            time += random.randomExp(lambdaPolisher);
            if (time < 480)
                cars.add(new PolisherCar(time));
        }

        Collections.sort(cars);

     //   System.out.println(cars);
    }

    public int getUnserviced() {
        return parking.getUnserviced();
    }

    public double getUtils() {

        return workers.getUtils()/(workers.getCountOfWorkers()*480);
    }


}
