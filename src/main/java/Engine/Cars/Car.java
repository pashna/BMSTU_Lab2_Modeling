package Engine.Cars;

import java.text.DecimalFormat;

/**
 * Created by popka on 20.04.15.
 */
public class Car implements Comparable{


    private int type; // "Цель визита"
    private double time; // Время события

    // Цели визита
    public final static int CAR_TO_WASHING = 1;
    public final static int CAR_TO_POLISHER = 2;

    // Состояния
    public final static int NOT_EXIST = 0;
    public final static int ARRIVED = 1;
    public final static int MAIN_PARKING = 2;
    public final static int WASHING_PARKING = 3;
    public final static int IN_PROCESS = 4;


    // Состояние
    private int state;

    public Car(double time, int type) {
        this.time = time;
        this.type = type;
        state = NOT_EXIST;
    }

    public int getType() {
        return type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object obj) {
        Car car = (Car) obj;
        if (this.time > car.time)
            return 1;
        if (this.time < car.time)
            return -1;
        else { // Если время одинаково, смотрим по статусу
            if (this.state > car.state)
                return -1;
            if (this.state < car.state)
                return 1;
            else
                return 0;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String stringType = type == 1 ? "Мойка":"Полировка";

        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(time) + " " +stringType;

    }

}
