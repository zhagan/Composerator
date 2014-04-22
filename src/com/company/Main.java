package com.company;

import com.company.Input_Processing.Audio_File;
import com.company.Input_Processing.MIDI_File;

public class Main {

    public static void main(String[] args) {

       System.out.println("Welcome to Composerator.");

        // Upload audio file
        String filePath = "/Users/garrettparrish/Desktop/Dat_Dere.mid";

        // initialize MIDI file
        MIDI_File datDere = new MIDI_File(filePath);

    }
}
