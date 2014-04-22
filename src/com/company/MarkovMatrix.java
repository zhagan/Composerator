package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

public class MarkovMatrix {

    public MarkovRow[] rows;
    public int columns;
    public BinTree mat;

    public MarkovMatrix(int range, int inc, int order) {
        // initializes the markov matrix with the correct range, inc, and order

		/*
		 *
		 * initializer (range, inc, order)
		 * rows = int (range/inc)^order // invariant from MarkovList : this will always come out to an int
  		 * cols = (range/inc) // also will be an int
  		 * mat = empty BinaryTree of MarkovRows // BinaryTree for now.  Will probably use a better data structure later
		 */
    }

    // the given cursor will have length order + 1
    public void update(Cursor c){
        // strip cursor of last entry (current note) = new_cursor, store last entry = cur_i
        // call BinTree.insert with id = new_cursor, index = cur_i
    }

	/*
	// returns row with specific id
	public MarkovRow lookup(int id){
		return ;
	}
	*/

}
