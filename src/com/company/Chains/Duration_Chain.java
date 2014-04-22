package com.company.Chains;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Chainables.Chainable;
import com.company.Chainables.Duration;

import java.util.ArrayList;
import java.util.List;

public class Duration_Chain implements Chain {

    private List<Duration> durations = new ArrayList<Duration>();

    public Duration_Chain()
    {

    }

    @Override
    public void add_to_chain(Chainable obj)
    {
        durations.add((Duration) obj);
    }

    @Override
    public void print_chain()
    {
        System.out.print("Durations (" + durations.size() + "): [");
        for (Duration d : durations)
        {
            System.out.print(" " + String.format("%1$,.2f", d.getTime()) + " ");
        }
        System.out.println("]");
    }

}