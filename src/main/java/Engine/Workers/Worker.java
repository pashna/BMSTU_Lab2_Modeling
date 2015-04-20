package Engine.Workers;

/**
 * Created by popka on 20.04.15.
 */
public class Worker implements Comparable{
    private double activeTime = 0;
    private double startTime;
    private double duration;

    public void seize(double startTime, double duration) {
        activeTime += duration;
        this.duration = duration;
        this.startTime = startTime;
    }

    public boolean isFree(double time) {
        if (startTime+duration < time)
            return true;
        return false;
    }

    public double getReleaseTime() {
        return startTime+duration;
    }

    @Override
    public int compareTo(Object obj) {
        Worker worker = (Worker) obj;
        double firstEnds = this.startTime+this.duration;
        double secondEnds = worker.startTime+worker.duration;
        if (firstEnds > secondEnds)
            return 1;
        if (firstEnds < secondEnds)
            return -1;
        else
            return 0;
    }
    
}
