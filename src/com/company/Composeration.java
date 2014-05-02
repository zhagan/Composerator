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

        // midi file to upload
        String midiFilePath = "/Users/garrettparrish/Desktop/Dat_Dere.mid";

        // load the sequence from the file path
        try
        {
            Sequence ms = MidiSystem.getSequence(new File(midiFilePath));

            // initialize MIDI file with the midi sequence
            MidiFile datDere_midi = new MidiFile(ms);

            // decode midi file to song
            Song datDere = datDere_midi.to_song();

            // pass song to encoder (use Markov chains)

            // ouput fromMarkov processing
            Song output = datDere;

            // create a midi file object from the song
            MidiFile datDere_midi_out = datDere.toMidiFile();

            // write the midi file out to path
            datDere_midi_out.writeToFilePath("/Users/garrettparrish/Desktop/midifileout.mid");

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
