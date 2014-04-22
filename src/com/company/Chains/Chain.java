package com.company.Chains;

/*
 * Created by Garrett on 4/22/14.
 */

import com.company.Chainables.Chainable;

public interface Chain {

    public void add_to_chain(Chainable obj);

    public void print_chain();
}
