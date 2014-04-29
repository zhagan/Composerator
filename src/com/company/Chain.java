package com.company;

/*
 * Created by Garrett on 4/22/14.
 */

import com.company.Chainables.Chainable;
import com.company.Cursor;
import java.util.ArrayList;

public class Chain<T extends Chainable> {

    // Instance vars
    private ArrayList<T> list;
    private Cursor cursor;

    // This is the position of the LAST element of the cursor
    private int cursor_pos;

    // Create new chain
    public Chain()
    {
        this.list = new ArrayList<T>();
    }

    // Add an object to the chain
    public void add_to_chain(T obj)
    {
        list.add(obj);
    }

    // Initialize the cursor (length order + 1) to the first elements
    public boolean init_cursor(int order)
    {
        if (list.size() >= cursor.getLen())
        {
            int len = order + 1;

            // copy first elements of list
            Chainable[] c = new Chainable[len];
            for (int i = 0; i < len; i++)
            {
                // TODO FIX THIS CAST --> SHOULDN'T HAVE TO DO IT THIS WAY
                c[i] = (Chainable) list.get(i);
            }

            cursor = new Cursor(len, c);
            cursor_pos = len - 1;

            return true;
        }

        return false;
    }

    // Advance cursor to next note, returns true if successful
    public boolean advance_cursor()
    {
        if (list.size() > cursor_pos)
        {
            for (int i = 0, l = cursor.getLen(); i < l; i++)
            {
                cursor.set(i, list.get(cursor_pos - l + 1));
            }

            return true;
        }

        return false;
    }

    // Print method to be implemented by inheritors
    // NOTE type T must have classToString method
    public void print_chain()
    {
        System.out.print(T.classToString() + " (" + list.size() + "): [");
        for (T c : list)
        {
            System.out.print(" " + c.toString() + " ");
        }
        System.out.println("]");
    }
}
