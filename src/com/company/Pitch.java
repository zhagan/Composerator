package com.company;

/*
 * Created by Garrett on 4/21/14.
 */
public class Pitch implements Chainable {

    // i.e. "C" or "B"
    private String pitch_class;

    // midi identifier
    private int midi_id;

    /*
     * midi_id corresponds to the following midi code taken from:
     * http://www.midimountain.com/midi/midi_note_numbers.html
     *
     * Octave |   C |  C# |   D |  D# |   E |   F |  F# |   G |  G# |   A |  A# |   B
     *      0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11
     *      1 |  12 |  13 |  14 |  15 |  16 |  17 |  18 |  19 |  20 |  21 |  22 |  23
     *      2 |  24 |  25 |  26 |  27 |  28 |  29 |  30 |  31 |  32 |  33 |  34 |  35
     *      3 |  36 |  37 |  38 |  39 |  40 |  41 |  42 |  43 |  44 |  45 |  46 |  47
     *      4 |  48 |  49 |  50 |  51 |  52 |  53 |  54 |  55 |  56 |  57 |  58 |  59
     *      5 |  60 |  61 |  62 |  63 |  64 |  65 |  66 |  67 |  68 |  69 |  70 |  71
     *      6 |  72 |  73 |  74 |  75 |  76 |  77 |  78 |  79 |  80 |  81 |  82 |  83
     *      7 |  84 |  85 |  86 |  87 |  88 |  89 |  90 |  91 |  92 |  93 |  94 |  95
     *      8 |  96 |  97 |  98 |  99 | 100 | 101 | 102 | 103 | 104 | 105 | 106 | 107
     *      9 | 108 | 109 | 110 | 111 | 112 | 113 | 114 | 115 | 116 | 117 | 118 | 119
     *     10 | 120 | 121 | 122 | 123 | 124 | 125 | 126 | 127
     *
     * Rest is given the midi value of 128 for the purpose of this program
     */

    // integer representing the pitch's octave on the piano (i.e. 4)
    private int octave;

    // int corresponding to the frequency of the pitch in Hertz
    private int frequency;

    // true/false if flat or sharp
    private boolean flat;
    private boolean sharp;

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

        if (note.contains("#")) { sharp = true; }
        if (note.contains("b")) { flat = true; }
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

    public boolean getFlat()
    {
        return flat;
    }

    public boolean getSharp()
    {
        return sharp;
    }
}