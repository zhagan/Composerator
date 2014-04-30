package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */
import java.util.ArrayList;
import com.company.Cursor;

public abstract class Chainable implements Comparable<Chainable> {

    // Method to determine the range, inc, and order of the chain based on
    // the specific type of chain. For example, if volumes from 0 to 0.5 were
    // used exclusively, it would cap the range of values to probalistically
    // determine to that range etc.
    // public void minimize();

    // A method to return a description of the value
    // Dummy method to be overriden
    // NEED TO CAST TO SUBCLASS TO USE OVERIDDEN METHODS
    public static String classToString()
    {
        return "";
    }

    // method to create an index list given a list of chainables
    // index list will be SORTED, QUANTIZED (if necessary), and CONTAIN NO DUPLICATES
    // Dummy method to be overriden
    public static ArrayList<Chainable> create_index(ArrayList<Chainable> chain)
    {
        return new ArrayList<Chainable>();
    }
}

