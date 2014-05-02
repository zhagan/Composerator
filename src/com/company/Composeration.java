package com.company;

/*
 * Created by garrettparrish on 5/2/14.
 */

import java.util.ArrayList;
import javax.sound.midi.*;
import java.io.File;

// class to encapsulate the operation of taking input songs and
public class Composeration
{

    // constructor for a composeration
    // filePaths: an array list of filepaths of input files
    // order: the desired order of the markov matrix
    // output length: the desired number of notes in the output file
    // outfilepath: path destination to which to write the otuput midi file
    public Composeration(ArrayList<String> filePaths, int order, int outputLength, String outFilePath)
    {

        // load the sequence from the file path
        try
        {
            // an array list to hold the multiple song objects from
            // each of the input midi files
            ArrayList<Song> songs = new ArrayList<Song>();

            // for each file passed as input -> create a midi sequence
            for(String path : filePaths)
            {
                // strip out the midi sequence
                Sequence ms = MidiSystem.getSequence(new File(path));

                // initialize MIDI file with the midi sequence
                MidiFile midi = new MidiFile(ms);

                // decode midi file to song
                Song midiSong = midi.to_song();

                // add to array list
                songs.add(midiSong);
            }

            // combine all the songs into one new one

            // set output song to be first song
            Song outputSong = songs.get(0);

            // if at least 2 songs
            if (songs.size() > 1)
            {
                // append 2nd song
                outputSong.appendSong(songs.get(1));
            }

            // if more than 2 (only in case of 3)
            if (songs.size() > 2)
            {
                // append the 3rd song (2nd song is already in the output)
                outputSong.appendSong(songs.get(2));
            }

            // pass output song to markov processing


            // ouput fromMarkov processing
//            Song output = new Song();

            // create a midi file object from the song
//            MidiFile datDere_midi_out = datDere.toMidiFile();

            // write the midi file out to path
  //          datDere_midi_out.writeToFilePath("/Users/garrettparrish/Desktop/midifileout.mid");

        }
        catch (Exception e)
        {
            // file doesn't exist
            System.out.println(e.toString());
        }
    }

    public void composerate()
    {
        System.out.println("DO MAIN FUNCTIONS");
    }
}
