package com.company;

/*
 * Created by Garrett on 4/24/14.
 */

import com.company.Chain;
import com.company.Chainables.*;
import java.util.*;

public class Cursor implements Comparable<Cursor>
{
    // Instance var
    private ArrayList<Chainable> entries;

    // Basic constructor
    public Cursor()
    {
        entries = new ArrayList<Chainable>();
    }

    // Constructor that also takes an input arraylist
    public Cursor(ArrayList<Chainable> list)
    {
        entries = list;
    }

    // Get ith entry
    public Chainable get_obj_at(int i)
    {
        return entries.get(i);
    }

    public int get_len()
    {
        return entries.size();
    }

    // Set ith entry
    public void set(int i, Chainable obj)
    {
        entries.set(i, obj);
    }

    // Get last entry
    public Chainable get_last()
    {
        return entries.get((entries.size() - 1));
    }

    // Return new chainable with last element removed
    public Cursor strip_last()
    {
        Cursor new_cur = new Cursor(entries);
        new_cur.entries.remove(entries.size() - 1);
        return new_cur;
    }

    // implementing compare method. ASSUMES same length
    public int compareTo(Cursor cur)
    {
        for (int i = 0, len = entries.size(); i < len; i++)
        {
            // compare corresponding entries
            int comp = entries.get(i).compareTo(cur.get_obj_at(i));

            if (comp < 0)
                return -1;

            else if (comp > 0)
                return 1;
        }
        return 0;
    }
}
