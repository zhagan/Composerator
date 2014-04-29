package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Input_Processing.MIDI_File;

public class Song {

    private Chain note_chain;
    private Chain volume_chain;
    private Chain duration_chain;
    private Chain pitch_chain;

    public Song (Chain pc, Chain vc, Chain dc, Chain nc)
    {
        pitch_chain = pc;
        volume_chain = vc;
        duration_chain = dc;
        note_chain = nc;

        print_song();
    }

    public void print_song()
    {
        pitch_chain.print_chain();
        volume_chain.print_chain();
        duration_chain.print_chain();
        note_chain.print_chain();
    }

    // converts the song back to a midi file

    /*

        GENERAL FORM OF A MIDI FILE

        - File header (14 constant bytes)
        - Track header (4 constant four bytes)
        - 4 bytes to indicate amount of track data (including footer) NOTE: big-endian
        - Track Data
            - Metadata events (tempo, key signature, time signature)
            - Performance events (notes, controller changes etc.)
        - Track footer (4 constant bytes)


        delta's and timebases: notes aren't specified by duration but by on-off time

     */

    public MIDI_File toMidi()
    {
        return new MIDI_File();
    }

}
