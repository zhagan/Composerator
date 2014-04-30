package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */

import java.util.ArrayList;

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

    // compares based on pitch only
    public int compareTo(Chainable n)
    {
        return pitch.compareTo(((Note) n).getPitch());
    }


    // ********* IMPORTANT ********
    // note class will probably become obsolete because the below don't make sense
    // will most likely process pitch / volume / duration independently
    // ****************************

    //TODO override. see description in chainable / use helpers below
    public static ArrayList<Chainable> create_index(ArrayList<Chainable> chain)
    {
        return sort(quantize(chain));
    }

    //TODO helper to quantize
    //this means there should be a discrete set of values (not necessarily integers)
    //i.e. [1.19,2.28,1.21] ==> [1.2,2.3,1.2]
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


