package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

// subclass of note
public class Rest extends Note {

    private double time_s;
    // define pitch as pre-determined rest value
    //	pitch =

    // define volume
    //	volume = new Volume
    public Rest(Duration d)
    {
        this.setPitch(new Pitch());
        this.setVolume(new Volume());
        this.setDuration(d);
    }

    public double getTime_s()
    {
        return time_s;
    }

}
