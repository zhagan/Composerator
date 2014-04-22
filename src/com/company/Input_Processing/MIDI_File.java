package com.company.Input_Processing;

/*
 * Created by Garrett on 4/22/14.
 */

import java.io.File;
import java.io.IOException;

import javax.sound.midi.*;

public class MIDI_File {

    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_CLASSES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public MIDI_File (String path)
    {
        // Import sequence from file path
        Sequence sequence = null;
        try
        {
            sequence = MidiSystem.getSequence(new File(path));
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        catch (InvalidMidiDataException e)
        {
            System.out.println(e.toString());
        }

        int trackNumber = 0;

        // iterate through available tracks
        for (Track track :  sequence.getTracks())
        {
            // for labeling track number
            trackNumber++;

            System.out.println("Track " + trackNumber + ": size = " + track.size());
            System.out.println();

            // iterate through each message in the track
            for (int i = 0; i < track.size(); i++)
            {
                // midi events occur at a specific 'tick rate' which is
                // analogous to sampling rate for an analog signal

                MidiEvent event = track.get(i);

                // if two events have the same tick then they occur at the same time
                // if there are ticks where there are no events - those are represented as rests

                System.out.print("@" + event.getTick() + " ");

                MidiMessage message = event.getMessage();

                if (message instanceof ShortMessage)
                {
                    ShortMessage sm = (ShortMessage) message;
                    System.out.print("Channel: " + sm.getChannel() + " ");

                    int key = sm.getData1();
                    int octave = (key / 12) - 1;
                    int note = key % 12;
                    String noteName = NOTE_CLASSES[note];
                    int velocity = sm.getData2();
                    String note_state = (sm.getCommand() == NOTE_ON) ? "Note on, " : "Note off,";
                    System.out.println(note_state + noteName + octave + " key = " + key + " velocity: " + velocity);
                }
                else
                {
                    System.out.println("Other message: " + message.getClass());
                }
            }
            System.out.println();
        }
    }
}
