package com.company;

/*
 * Created by Garrett on 4/21/14.
 */
public class Pitch implements Chainable {

    // i.e. "C" or "B"
    private String pitch_class;

    // midi identifier
    private int midi_id;

    // i.e. 4 corresponding to the 4th octave
    private int octave;

    // int corresponding to the frequency of the pitch in Hertz
    private int frequency;

    // true/false if flat or sharp
    public boolean flat;
    public boolean sharp;

    // Default constructor for rest pitches
    public Pitch()
    {
        // rest values
        pitch_class = "R";
        octave = 0;
        midi_id = 128;
    }

    // Pitch note constructor
    public Pitch (String note, int oct, int id)
    {
        octave = oct;
        pitch_class = note;
        midi_id = id;
    }

    // description method to print
    @Override
    public String desc()
    {
        return "Pitch: " + pitch_class + octave + " ";
    }

    // getter methods for instance variables
    public String getPitch_class()
    {
        return pitch_class;
    }

    public int getOctave()
    {
        return octave;
    }


}
