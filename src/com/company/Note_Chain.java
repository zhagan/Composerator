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


//    @Override
    public void add_to_chain(Chainable obj)
    {
        notes.add((Note) obj);
    }

    // array of notes
    //private Note[];

}
