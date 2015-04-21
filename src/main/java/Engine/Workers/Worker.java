package Engine.Workers;

/**
 * Created by popka on 20.04.15.
 */
public class Worker implements Comparable{
    private double activeTime = 0;
    private double startTime;
    private double duration;

    /*
    Занять рабочего начиная с времени StartTime на duration единиц времени
     */
    public void seize(double startTime, double duration) {
        activeTime += duration;
        this.duration = duration;
        this.startTime = startTime;
    }

    /*
    Свободен ли рабочий в момент времени time
     */
    public boolean isFree(double time) {
        if (startTime+duration < time)
            return true;
        return false;
    }

    /*
    Возвращает время освобождения рабочего
     */
    public double getReleaseTime() {
        return startTime+duration;
    }

    /*
    Возвращает время, когда рабочий работал (для utils)
     */
    public double getActiveTime() {
        return activeTime;
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
