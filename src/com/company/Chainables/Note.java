package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */

public class Note extends Chainable {

    // a note's pitch
    private Pitch pitch;

    // duration
    private Duration duration;

    // volume
    private Volume volume;

    private static String newline = "\n";

    public Note(Pitch p, Volume v, Duration d)
    {
        pitch = p;
        volume = v;
        duration = d;
    }


    // default constructor
    public Note()
    {
        pitch = null;
        duration = null;
        volume = null;
    }

    // getter methods for instance variables
    public Pitch getPitch()
    {
        return pitch;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public Volume getVolume()
    {
        return volume;
    }

    // setter methods (mainly used by the rest subclass)
    public void setPitch(Pitch p)
    {
        pitch = p;
    }

    public void setVolume(Volume v)
    {
        volume = v;
    }

    public void setDuration(Duration d)
    {
        duration = d;
    }

    // get class name
    public static String classToString()
    {
        return "Note";
    }

    @Override
    public String toString()
    {
        return "(" + pitch.toString() + ", " + duration.toString() + ", " + volume.toString() + ")";
    }

    // TODO
    public int compareTo(Chainable n)
    {
        return 0;
    }
}


