package com.company;

/*
 * Created by Garrett on 4/22/14.
 */

import com.company.*;
import com.company.Chainables.*;
import java.io.*;
import javax.sound.midi.*;

public class MidiFile {

    // variable used to decode the midi file
    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;
    private static final String[] NOTE_CLASSES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final int NOTES_PER_OCTAVE = 12;

    // midi sequence
    private Sequence midiSequence;

    // timing resolution
    private float timingResolution;

    // default constructor
    public MidiFile()
    {
        midiSequence = null;
    }

    // constructor for a MidiFile object
    public MidiFile(Sequence s)
    {
        // Import sequence from file path (catch exceptions)
        // set sequence
        midiSequence = s;

        // extract timing resolution
        timingResolution = (float) midiSequence.getResolution();
    }

    // decodes the midi file to a song object
    public Song to_song()
    {
        // initialize all the chain variables
        Chain pitch_chain = new Chain<Pitch>();
        Chain volume_chain = new Chain<Volume>();
        Chain duration_chain = new Chain<Duration>();
        Chain note_chain = new Chain<Note>();

        // iterate through MIDI tracks
        int trackNumber = 0;

        // iterate through available tracks
        for (Track track :  midiSequence.getTracks())
        {

            // for labeling track number
            trackNumber++;

            // info tracks usually have a small track size (around 5 - 10 pieces of info)
            // actual music tracks are generally on the scale of hundreds
            System.out.println("Track " + trackNumber + ": size = " + track.size());
            System.out.println();

            // starting and ending ticks of a MIDI note
            long note_tick_start = 0;
            long note_tick_end;

            // starting and ending ticks of a MIDI rest
            long rest_tick_start = 0;
            long rest_tick_end;

            // starting and ending velocities of MIDI note
            int velocity_start = 0;
            int velocity_end;

            // iterate through each message in the track
            for (int i = 0; i < track.size(); i++)
            {
                // midi events occur at a specific 'tick rate' which is
                // analogous to sampling rate for an analog signal
                MidiEvent event = track.get(i);

                // tick is the clock value of the midi event
                long tick = event.getTick();

                // Java's midi library encodes midi events as 'messages'
                MidiMessage message = event.getMessage();

                if (message instanceof ShortMessage)
                {
                    ShortMessage sm = (ShortMessage) message;

                    // key pressed
                    int key = sm.getData1();

                    // note's octave
                    int octave = (key / NOTES_PER_OCTAVE) - 1;

                    // note class
                    int note = key % NOTES_PER_OCTAVE;
                    String noteName = NOTE_CLASSES[note];

                    // note's velocity (eventually mapped to volume)
                    int velocity = sm.getData2();

                    String note_state = null;

                    // NOTE STATES
                    if (sm.getCommand() == NOTE_ON)
                    {
                        // note has begun to be registered, rest has ended
                        note_tick_start = tick;
                        velocity_start = velocity;
                        rest_tick_end = tick;

                        // insert a rest object into all 4 chains with rest-specific values for each

                        // initialize new rest with just a duration (default values of
                        // pitch and volume are encapsulated in the rest constructor)
                        Duration rest_duration = new Duration(rest_tick_start, rest_tick_end, timingResolution);

                        // only generate and add the rests if they last for a usable amount of time
                        if (rest_duration.getTime() > 0.0)
                        {
                            // create a new rest object (using duration constructor)
                            Rest current_rest = new Rest(rest_duration);

                            // add rest versions of each object to their respective chains
                            pitch_chain.add_to_chain(new Pitch());
                            volume_chain.add_to_chain(new Volume());
                            duration_chain.add_to_chain(rest_duration);

                            // add raw rest (since it's a subclass of Note) to the note chain
                            note_chain.add_to_chain(current_rest);
                        }
                    }
                    else if (sm.getCommand() == NOTE_OFF)
                    {
                        // note has registered, rest has begun
                        note_tick_end = tick;
                        velocity_end = velocity;
                        rest_tick_start = tick;

                        // create a new pitch object
                        Pitch current_pitch = new Pitch(noteName, octave, key);

                        // create a new volume object
                        Volume current_volume = new Volume(velocity_start, velocity_end);

                        // create a new duration object (passing timing resolution)
                        Duration current_duration = new Duration(note_tick_start, note_tick_end, timingResolution);

                        // encapsulate a new note object
                        Note current_note = new Note(current_pitch, current_volume, current_duration);

                        // print out current_note description
                        System.out.println(current_note.toString());

                        // add current values to chains
                        pitch_chain.add_to_chain(current_pitch);
                        volume_chain.add_to_chain(current_volume);
                        duration_chain.add_to_chain(current_duration);
                        note_chain.add_to_chain(current_note);
                    }
                    else
                    {
                        System.out.println("Other command: " + sm.getCommand());
                    }
                }
                else
                {
                    System.out.println("Other message: " + message.getClass());
                }
            }
            System.out.println();
        }

        // return the compiled song (also print song) (pass in timing resolution for output reasons)
        return new Song(pitch_chain, volume_chain, duration_chain, note_chain, timingResolution);
    }

    // getter method for timing resolution
    public float getTimingResolution()
    {
        return timingResolution;
    }

    // creates an actual midi file and writes it to the directory specified
    public void writeToFilePath(String filePath)
    {
        // write midi sequence to file
        File f = new File(filePath);
        try
        {
            MidiSystem.write(midiSequence, 1, f);
        }
        // file wasn't successfully written
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }

}
