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
    public Composeration(ArrayList<String> filePaths, int order, int outputLength, String outFilePath)
    {
        // filePaths: an array list of filepaths of input files
        // order: the desired order of the markov matrix
        // output length: the desired number of notes in the output file
        // outfilepath: path destination to which to write the otuput midi file

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

            // make an array of the songs
            Song[] songArray = (Song[]) songs.toArray();

            Song outputSong = songArray[0];

            // iterate through rest of songs
            for (int i = 1; i < songArray.length; i++)
            {
                outputSong.appendSong(songArray[i]);
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
