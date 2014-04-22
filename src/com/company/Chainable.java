package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

public interface Chainable {

    // Method to determine the range, inc, and order of the chain based on
    // the specific type of chain. For example, if volumes from 0 to 0.5 were
    // used exclusively, it would cap the range of values to probalistically
    // determine to that range etc.
    public void minimize();

}
