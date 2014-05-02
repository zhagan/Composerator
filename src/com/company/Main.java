package com.company;

import com.company.UI.FileChooser;
import com.company.UI.UIFrame;

import javax.sound.midi.*;
import java.io.File;
import com.company.Chainables.*;

// TODO will be unused
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Composerator.");

        // midi file to upload
        String midiFilePath = "/Users/powderski11/Downloads/Dat_Dere.mid";

        // load the sequence from the file path
        try
        {
            Sequence ms = MidiSystem.getSequence(new File(midiFilePath));

            // initialize MIDI file with the midi sequence
            MidiFile datDere_midi = new MidiFile(ms);

            // decode midi file to song
            Song datDere = datDere_midi.to_song();

            // create probability matrices for each element of song
            MarkovMatrix<Pitch> pitchMatrix = new MarkovMatrix<Pitch>(datDere.getPitch_chain(), 2);
            MarkovMatrix<Duration> durationMatrix = new MarkovMatrix<Duration>(datDere.getDuration_chain(), 2);
            MarkovMatrix<Volume> volumeMatrix = new MarkovMatrix<Volume>(datDere.getVolume_chain(), 2);

            // composerate!
            Chain<Pitch> pitchChain = pitchMatrix.compose(100);
            Chain<Duration> durationChain = durationMatrix.compose(100);
            Chain<Volume> volumeChain = volumeMatrix.compose(100);

            // ouput fromMarkov processing
            Song output = new Song(pitchChain, volumeChain, durationChain, null, (float) 0.);

            // create a midi file object from the song
            MidiFile datDere_midi_out = output.toMidiFile();

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
