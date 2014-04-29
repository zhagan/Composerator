package com.company;

import com.company.Input_Processing.MIDI_File;

public class Main {

    public static void main(String[] args) {

       System.out.println("Welcome to Composerator.");

        // Upload audio file
        String filePath = "/Users/powderski11/Downloads/Dat_Dere.mid";

        // initialize MIDI file
        MIDI_File datDere_midi = new MIDI_File(filePath);

        // decode midi file to song
        Song datDere = datDere_midi.to_song();

        // pass song to encoder (use Markov chains)

    }
}
