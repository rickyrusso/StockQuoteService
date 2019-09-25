package edu.rrusso.advancedjava;

public enum Interval {
    HALF_HOUR(30), HOURLY(60), DAILY(1440);

    private final int minuteInterval;

    Interval(int i) {
        this.minuteInterval = i;
    }
    public int MinuteInterval(){
        return this.minuteInterval;
    }
}