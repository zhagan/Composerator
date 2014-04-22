package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

public class Note implements Chainable {

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

    public String desc()
    {
        return pitch.desc() + newline + volume.desc() + newline + duration.desc() + newline;
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




}


