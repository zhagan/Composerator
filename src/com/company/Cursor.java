package com.company;

/*
 * Created by Garrett on 4/24/14.
 */

import com.company.Chain;
import com.company.Chainables.*;

public class Cursor implements Comparable<Cursor>
{
    // Instance vars
    private int len;
    private Chainable[] entries;

    // Basic constructor. Assumes positive len
    public Cursor(int len)
    {
        len = len;
        entries = new Chainable[len];
    }

    // Constructor that also takes an input array
    public Cursor(int len, Chainable[] arr)
    {
        this(len);

        // only add the first len elements
        for (int i = 0; i < len; i++)
        {
            entries[i] = arr[i];
        }
    }

    // Get len
    public int getLen()
    {
        return len;
    }

    // Get ith entry
    public Chainable get_obj_at(int i)
    {
        return entries[Math.max(Math.min(i, len - 1), 0)];
    }

    // Set ith entry
    public void set(int i, Chainable obj)
    {
        entries[i] = obj;
    }

    // Get last entry
    public Chainable get_last()
    {
        return this.get_obj_at(len - 1);
    }

    // Return new chainable with last element removed
    public Cursor strip_last()
    {
        int new_len = len - 1;
        Chainable[] new_c = new Chainable[new_len];
        System.arraycopy(entries, 0, new_c, 0, new_len);
        return new Cursor(new_len, new_c);
    }

    // implementing compare method. assumes same length
    @Override public int compareTo(Cursor c)
    {

        // extra precaution in case lengths differ
        int min_len = Math.min(len, c.getLen());

        for (int i = 0; i < min_len; i++)
        {
            // compare corresponding entries
            int comp = this.get_obj_at(i).compareTo(c.get_obj_at(i));

            if (comp < 0)
                return -1;

            else if (comp > 0)
                return 1;
        }
        return 0;
    }
}
