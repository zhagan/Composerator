package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Chainables.Chainable;

import java.util.ArrayList;

public class Duration extends Chainable {

    // length of the duration
    private double time_s;
    private long tick_length;

    // tolerance for comparison
    private final double tolerance = 1e-6;

    // tick rate
    // MIDI files were generated at 960 PPQN (pulses per quarter note)
    private static final double PPQN = 960.0;

    // THIS IS EMPIRICALLY DETERMINED FROM THE INPUT FILE BUT NEED TO FIND
    // A WAY OF DEDUCING IT FROM THE MIDI FILE

    // in quarter / min
    private static final double tempo = 100.0;

    // pulses per second (1600 @ 100 bpm)
    private static final double tick_rate = (tempo / 60.0) * PPQN;

    // main constructor that takes starting and ending tick values
    public Duration(long tick1, long tick2)
    {
        tick_length = tick2 - tick1;
        // do math to determine actual duration in seconds
        time_s = (tick_length / tick_rate);
    }

    public double getTime()
    {
        return time_s;
    }

    public long getTick_length()
    {
        return tick_length;
    }

    public String desc()
    {
        return "Time: " + Double.toString(time_s) + "s ";
    }

    @Override
    public String toString()
    {
        return String.format("%1$,.2f", time_s);
    }

    // get class name
    public static String classToString()
    {
        return "Duration";
    }

    // ASSUMES chainable is a duration
    public int compareTo(Chainable d)
    {
        double diff = time_s - ((Duration) d).getTime();

        if (Math.abs(diff) < tolerance) return 0;

        else if (diff < 0) return -1;

        else return 1;
    }

    //TODO override. see description in chainable / use helpers below
    public static ArrayList<Chainable> create_index(ArrayList<Chainable> chain)
    {
        return sort(quantize(chain));
    }

    //TODO helper to quantize
    //this means there should be a discrete set of values (not necessarily integers)
    //i.e. [1.19,2.28,1.21] ==> [1.2,2.3,1.2]; close values become the same
    //careful with casting here and below. will need to cast from chainable to vol
    private static ArrayList<Chainable> quantize(ArrayList<Chainable> chain)
    {
        return new ArrayList<Chainable>();
    }

    //TODO helper to sort and remove duplicates
    //should be self explanatory. sorting is done using compareTO
    private static ArrayList<Chainable> sort(ArrayList<Chainable> chain)
    {
        return new ArrayList<Chainable>();
    }
}
