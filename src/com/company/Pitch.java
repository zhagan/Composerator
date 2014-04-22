package com.company;

/*
 * Created by Garrett on 4/21/14.
 */
public class Pitch implements Chainable {

    // i.e. "C" or "B"
    private String noteClass;

    // midi identifier
    private int midi_id;

    // i.e. 4 corresponding to the 4th octave
    private int octave;

    // int corresponding to the frequency of the pitch in Hertz
    private int frequency;

    // true/false if flat or sharp
    public boolean flat;
    public boolean sharp;

    // Pitch constructor
    public Pitch (String note, int oct, int id)
    {
        octave = oct;
        noteClass = note;
        midi_id = id;
    }

    public String desc()
    {
        return "Pitch: " + noteClass + octave + " ";
    }

}
