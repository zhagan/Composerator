package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

public class Volume implements Chainable {

    // level of the volume
    public double vol;

    // upper and lower bounds of volume range
    private static final double upperBound = 1.0;
    private static final double lowerBound = 0.0;

    // Java MIDI library's double range of velocities
    private static final double velocity_range = 127.0;

    // Default constructor (for rests)
    public Volume()
    {
        vol = 0.0;
    }

    // Standard constructor (for notes w/ input velocities)
    public Volume(int start_velocity, int end_velocity)
    {
        // no end velocity -- map just starting velocity
        if (end_velocity == 0)
        {
            // Map 0.0 - 127.0 to 0.0 to 1.0
            vol = (upperBound - lowerBound) * (start_velocity / velocity_range);
        }
        else
        {
            System.out.println("REST");
            // add algorithm to average velocities if need be
        }
        // linearly map velocity to volume
    }

    public double getVol()
    {
        return vol;
    }

    public String desc()
    {
        return "Vol: " + Double.toString(vol) + " ";
    }

}
