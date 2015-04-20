package Engine.Workers;

import java.util.Arrays;

/**
 * Created by popka on 20.04.15.
 */
public class Workers {

    private int countOfWorkers;
    private Worker[] workers;


    public Workers(int countOfWorkers) {
        this.countOfWorkers = countOfWorkers;
        workers = new Worker[countOfWorkers];
        for (int i=0; i<countOfWorkers; i++) {
            workers[i] = new Worker();
        }
    }

    public int getCountOfWorkers() {
        return countOfWorkers;
    }

    public double getUtils() {
        double activeTime = 0;
        for (int i=0; i<countOfWorkers; i++) {
            activeTime += workers[i].getActiveTime();
        }
        return activeTime;
    }

    public boolean isFree(double time, int count) {
        int freeCount = 0;
        for (int i=0; i<countOfWorkers; i++) {
            if (workers[i].isFree(time))
                freeCount++;
        }
        if (freeCount >= count)
            return true;
        else
            return false;
    }

    public void enter(int count, double time, double duration) {
        int seizedCount = 0;
        for (int i=0; i<countOfWorkers; i++) {
            if (workers[i].isFree(time)) {
                workers[i].seize(time, duration);
                seizedCount++;
            }
            if (seizedCount == count)
                break;
        }
    }

    public double getNearFreeTime(int count) {
        Arrays.sort(workers);
        return workers[count-1].getReleaseTime() + 0.00001;
    }

}
