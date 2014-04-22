package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import java.util.ArrayList;
import java.util.List;

public class Duration_Chain implements Chain {

    private List<Duration> durations = new ArrayList<Duration>();

    public Duration_Chain()
    {

    }

//    @Override
    public void add_to_chain(Chainable obj)
    {
        durations.add((Duration) obj);
    }


}