package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

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

}
