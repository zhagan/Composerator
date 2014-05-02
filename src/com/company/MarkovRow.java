package com.company;

/*
 * Created by Garrett on 4/21/14.
 */
import java.util.ArrayList;
import com.company.Chainables.Chainable;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class MarkovRow<T extends Chainable> implements Comparable<MarkovRow>
{
    // identifier, i.e. note(s) that we are finding the probability
    // of certain notes following
    private Cursor id;

    // to keep track of the number of nonzero probabilities
    private int num_filled;

    // list of probabilities
    private ArrayList<Double> entries;

    // index list, for reference
    private ArrayList<T> index;

    // keep track of normalized
    // also keeps track of whether the matrix is "done" (fully constructed)
    private Boolean normalized = false;

    // getters
    public Cursor getId()
    {
        return id;
    }

    public int getNum_filled()
    {
        return num_filled;
    }

    public ArrayList<Double> getEntries()
    {
        return entries;
    }

    public ArrayList<T> getIndex()
    {
        return index;
    }

    public MarkovRow (Cursor i, ArrayList<T> ind)
    {
        index = ind;
        id = i;
        num_filled = 0;

        // entries initially zero
        for (Chainable c : index)
        {
            entries.add(0.);
        }
    }

    // TODO -- looks up the index of a chainable
    // binary search for index (index is sorted w/ no duplicates)
    // return -1 if not found in the index, though this should never happen
    // (index should contain all possible chainables in the song)
    // for use in inc() below
    private int find_index(Chainable c)
    {
        return 0;
    }

    // adds 1 to a slot that corresponds to a give chainable
    public void inc(Chainable c)
    {
        int index = find_index(c);
        entries.set(index, 1.);

        // update num_filled if this is the first entry in that slot
        if ((int) Math.round(entries.get(index)) == 1)
        {
            num_filled++;
        }

    }

    // Method to divide each element of the row by the number of entries that are filled
    public void normalize()
    {
        for (int i = 0, n = entries.size(); i < n; i++)
        {
            entries.set(i, (entries.get(i) / num_filled));
        }

        normalized = true;
    }

    // TODO
    // randomly pick a note from the row based on the probabilities
    // return null if not normalized (shouldn't happen anyway)
    // commented to avoid warnings
    //public Chainable pick()
    {

    }


    // Compare two rows by ID
    public int compareTo(MarkovRow r)
    {
        return id.compareTo(r.getId());
    }
}
