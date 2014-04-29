package com.company;

import com.company.UI.FileChooser;
import com.company.UI.UIFrame;

import javax.sound.midi.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Composerator.");

        // SOME UI ATTEMPTS
        // create a file chooser
//        FileChooser chooser = new FileChooser();
//        chooser.main(null);
//        UIFrame demo = new UIFrame();
//        demo.startup();


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
}
