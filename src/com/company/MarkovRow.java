package com.company;

/**
 * Created by garrettparrish on 4/21/14.
 */
public class MarkovRow {

    public Cursor id;
    public int num_filled;
    public int[] entries;

    public MarkovRow (Cursor c, int num_cols) {
        id = c;

        // fill entries with zeros
        for (int i = 0; i < num_cols; i++){
            entries[i] = 0;
        }

        num_filled = 0;
    }

    public void update(int index){
        entries[index]++;
        num_filled++;
    }

    // Method to divide each element of the row by the number of entries that are filled
    public void normalize(){
        for(int e = 0; e < entries.length; e++){
            if (entries[e] != 0){
                entries[e] /= num_filled;
            }
        }
    }

    // Used to compare two rows by id
    public void compare(){

    }

    // Test NO message

}
