package models;

/**
 * Created by samuelhooker on 27/03/17.
 */
public class StopAndTime{
    @Override
    public String toString() {
        return stop+". Time: "+time;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StopAndTime(Stop stop) {
        this.stop = stop;
    }

    public Stop stop;
    public String time = "";
}