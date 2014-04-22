package com.company.Chains;

import com.company.Chainables.Chainable;
import com.company.Chainables.Pitch;

import java.util.*;

/*
 * Created by Garrett on 4/22/14.
 */

public class Pitch_Chain implements Chain {

    private List<Pitch> pitches = new ArrayList<Pitch>();

    public Pitch_Chain()
    {

    }

    @Override
    public void add_to_chain(Chainable obj)
    {
        pitches.add((Pitch) obj);
    }

    @Override
    public void print_chain()
    {
        System.out.print("Pitches (" + pitches.size() + "): [");
        for (Pitch p : pitches)
        {
            System.out.print(" " + p.getPitch_class() + p.getOctave() + " ");
        }
        System.out.println("]");
    }

}
