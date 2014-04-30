package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Chainables.Chainable;
import com.company.Chainables.Pitch;

public class BinTree<T extends MarkovRow>
{
    // instance vars
    // for leaves, left / right are set to null
    private BinTree<T> left;
    private BinTree<T> right;
    private MarkovRow row;

    public BinTree()
    {

    }

    // TODO
    // this is called whenever we get a new pitch/duration/vol from the chain
    // insert a row and maintain sorted
    // if row exists, update row using MarkovRow inc function
    // r is the row, c is the pitch/dur/vol to be inserted
    // MAY NEED TO HANDLE a case where c = null, meaning we have an empty row
    public void insert(MarkovRow r, Chainable c)
    {

    }

    // TODO (easy)
    // normalize every row in the tree
    public void normalize()
    {

    }

    // TODO
    // find and return the MarkovRow with the given ID
    // commented out to avoid warning
//    public MarkovRow get(Cursor id)
    {

    }


}
