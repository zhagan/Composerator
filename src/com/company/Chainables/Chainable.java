package com.company.Chainables;

/*
 * Created by Garrett on 4/21/14.
 */

//Cameron test commit 4/29/14

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


}

