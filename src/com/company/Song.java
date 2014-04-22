package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Chains.Duration_Chain;
import com.company.Chains.Note_Chain;
import com.company.Chains.Pitch_Chain;
import com.company.Chains.Volume_Chain;

public class Song {

    private Note_Chain note_chain;
    private Volume_Chain volume_chain;
    private Duration_Chain duration_chain;
    private Pitch_Chain pitch_chain;

    public Song (Pitch_Chain pc, Volume_Chain vc, Duration_Chain dc, Note_Chain nc)
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
