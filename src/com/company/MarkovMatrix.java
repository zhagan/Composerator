package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import com.company.Chainables.Chainable;
import java.util.ArrayList;

public class MarkovMatrix<T extends Chainable>
{
    // root of tree (can reach the rest by traversal)
    private BinTree<MarkovRow> root;

    // index array that shows what notes/durs/vols in the rows refer to
    private ArrayList<T> index;

    // actual matrix of rows
    private ArrayList<MarkovRow<T>> matrix;

    // **** NOTE ****
    // it will probably be good to use helpers for these in the spirit of good design

    // TODO
    // builds a matrix (tree) from a chain
    // will be used in main to build matrices for pitch/dur/vol chains in song
    // inits cursor in chain
    // iterates thru the chain, advancing the cursor until c.advance_cursor returns false (reached the end)
    // ^ use a while loop for this, can check and advance the cursor at the same time
    // each cursor is advanced, update tree
    // ID's for rows in the tree are the cursor stripped of the last element; we want the notes preceding,
    // not the current note (theres a method for that in cursor)
    // once done, normalize the tree
    public MarkovMatrix(Chain c, int order)
    {
        // start cursor
        c.init_cursor(order);
    }

    // TODO
    // use the matrix to compose a piece of a given length!!!!! :):)
    // piece should begin with a random ("random") selection from the ID's of MarkovRows
    // have a cursor variable outside the loop, length order (not order + 1)
    // then looping length - 2 times (to account for the start), keep picking the next note
    // and advancing your cursor by one (you have to do this manually, no outside method this time)
    public Chain<T> compose(int length)
    {
        return new Chain<T>();
    }
}
