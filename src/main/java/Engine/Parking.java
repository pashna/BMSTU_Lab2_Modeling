package Engine;

import Engine.Cars.Car;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by popka on 20.04.15.
 */
public class Parking {
    private LinkedList<Car> carQueue;
    private int maxCount;
    private int unserviced = 0;


    public Parking(int maxCount) {
        this.maxCount = maxCount;
        carQueue = new LinkedList<Car>();
    }

    public int getUnserviced() {
        return unserviced;
    }

    public boolean add(Car car) {
        if (carQueue.size() < maxCount) {
            carQueue.addLast(car);
            return true;
        }
        else return false;
    }

    public Car removeFirst() {
        return carQueue.removeFirst();
    }

    public Car getFirst() {
        return carQueue.getFirst();
    }

    public boolean isFull() {
        return maxCount == carQueue.size();
    }

    public void incrementUnserviced() {
        unserviced++;
    }

    public void clear() {
        unserviced = 0;
        carQueue.clear();
    }

}
