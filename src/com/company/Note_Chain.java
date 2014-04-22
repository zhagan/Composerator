package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import java.util.ArrayList;
import java.util.List;

public class Note_Chain implements Chain {

    private List<Note> notes = new ArrayList<Note>();

    public Note_Chain()
    {

    }


    @Override
    public void add_to_chain(Chainable obj)
    {
        notes.add((Note) obj);
    }

    @Override
    public void print_chain()
    {
        System.out.print("Notes: [");
        for (Note n : notes)
        {
            System.out.print(" (" + n.getPitch().getPitch_class() + n.getPitch().getOctave() + ", " +
                    String.format("%1$,.2f", n.getVolume().getVol()) + ", " + String.format("%1$,.2f",n.getDuration().getTime()) + "s)");
        }
        System.out.println("]");
    }

}
