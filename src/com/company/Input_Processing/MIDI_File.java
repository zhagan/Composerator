package com.company.Input_Processing;

/*
 * Created by Garrett on 4/22/14.
 */

import com.company.*;

import java.io.*;
import javax.sound.midi.*;

public class MIDI_File {

    // variable used to decode the midi file
    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;
    private static final String[] NOTE_CLASSES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    private Sequence midiSequence;

    // constructor for a MIDI_File object
    public MIDI_File (String path)
    {
        // Import sequence from file path
        midiSequence = null;
        try {
            midiSequence = MidiSystem.getSequence(new File(path));
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (InvalidMidiDataException e) {
            System.out.println(e.toString());
        }
    }

    // decodes the midi file to a song object -- returns song object
    public void /*Song*/ decode_to_chains()
    {
        // initialize all the chain variables
        initialize_chains();

        // MIDI files have multiple 'tracks' corresponding
        // to independent data streams
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

            // used to determine the tick count between note_on and note_off events
            long tick_buffer = 0;
            boolean note_buffer = false;

            // starting and ending ticks of a MIDI note
            long note_tick_start = 0;
            long note_tick_end = 0;

            // starting and ending ticks of a MIDI rest
            long rest_tick_start = 0;
            long rest_tick_end = 0;

            // starting and ending velocities of MIDI note
            int velocity_start = 0;
            int velocity_end = 0;

            // iterate through each message in the track
            for (int i = 0; i < track.size(); i++)
            {
                // midi events occur at a specific 'tick rate' which is
                // analogous to sampling rate for an analog signal
                MidiEvent event = track.get(i);

                // tick is the clock value of the midi event
                long tick = event.getTick();

                // if two events have the same tick then they occur at the same time
                // if there are ticks where there are no events - those are represented as rests
//                System.out.println("@" + tick + " ");

                // Java's midi library encodes midi events as 'messages'
                MidiMessage message = event.getMessage();

                if (message instanceof ShortMessage)
                {
                    ShortMessage sm = (ShortMessage) message;
//                    System.out.println("Channel: " + sm.getChannel() + " ");

                    // the key pressed
                    int key = sm.getData1();

                    /* corresponds to the following midi code taken from:
                     * http://www.midimountain.com/midi/midi_note_numbers.html
                     *
                     * Octave |   C |  C# |   D |  D# |   E |   F |  F# |   G |  G# |   A |  A# |   B
                     *      0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11
                     *      1 |  12 |  13 |  14 |  15 |  16 |  17 |  18 |  19 |  20 |  21 |  22 |  23
                     *      2 |  24 |  25 |  26 |  27 |  28 |  29 |  30 |  31 |  32 |  33 |  34 |  35
                     *      3 |  36 |  37 |  38 |  39 |  40 |  41 |  42 |  43 |  44 |  45 |  46 |  47
                     *      4 |  48 |  49 |  50 |  51 |  52 |  53 |  54 |  55 |  56 |  57 |  58 |  59
                     *      5 |  60 |  61 |  62 |  63 |  64 |  65 |  66 |  67 |  68 |  69 |  70 |  71
                     *      6 |  72 |  73 |  74 |  75 |  76 |  77 |  78 |  79 |  80 |  81 |  82 |  83
                     *      7 |  84 |  85 |  86 |  87 |  88 |  89 |  90 |  91 |  92 |  93 |  94 |  95
                     *      8 |  96 |  97 |  98 |  99 | 100 | 101 | 102 | 103 | 104 | 105 | 106 | 107
                     *      9 | 108 | 109 | 110 | 111 | 112 | 113 | 114 | 115 | 116 | 117 | 118 | 119
                     *     10 | 120 | 121 | 122 | 123 | 124 | 125 | 126 | 127
                     */

                    // note's octave
                    int octave = (key / 12) - 1;

                    // get note class
                    int note = key % 12;
                    String noteName = NOTE_CLASSES[note];

                    // velocity can be linearly mapped to volume
                    // ranges from 0 to 127
                    int velocity = sm.getData2();

                    String note_state = null;

                    // NOTE STATES
                    if (sm.getCommand() == NOTE_ON)
                    {
                        // NOTE HAS BEGUN TO BE REGISTERED
                        note_state = "Note on, ";
                        note_tick_start = tick;
                        velocity_start = velocity;
                        rest_tick_end = tick;

                        generate_rest(rest_tick_start, rest_tick_end);
                    }
                    else if (sm.getCommand() == NOTE_OFF)
                    {
                        // NOTE HAS BEEN FULLY REGISTERED

                        note_state = "Note off, ";
                        note_tick_end = tick;
                        velocity_end = velocity;
                        rest_tick_start = tick;

                        // create a new pitch object
                        Pitch current_pitch = new Pitch(noteName, octave, key);

                        // create a new volume object
                        Volume current_volume = new Volume(velocity_start, velocity_end);

                        // create a new duration object
                        Duration current_duration = new Duration(note_tick_start, note_tick_end);

                        // encapsulate a new note object
                        Note current_note = new Note(current_pitch, current_volume, current_duration);

                        System.out.println(current_note.desc());

                        // add current values to chains
                        pitch_chain.add_to_chain(current_pitch);
                        volume_chain.add_to_chain(current_volume);
                        duration_chain.add_to_chain(current_duration);
                        note_chain.add_to_chain(current_note);

                    }
                    else
                    {
//                        System.out.println("Other command: " + sm.getCommand());
                    }

//                    System.out.println(note_state + noteName + octave + " key = " + key + " velocity: " + velocity);

                }
                else
                {
                    System.out.println("Other message: " + message.getClass());
                }
            }
            System.out.println();
        }

        // export song to Markov Chain mixers
        produce_song();
    }

    private Pitch_Chain pitch_chain;
    private Volume_Chain volume_chain;
    private Duration_Chain duration_chain;
    private Note_Chain note_chain;

    private void initialize_chains()
    {
        pitch_chain = new Pitch_Chain();
        volume_chain = new Volume_Chain();
        duration_chain = new Duration_Chain();
        note_chain = new Note_Chain();

        // REST VALUES FOR INSERTION INTO CHAINS

    }

    private void add_current_values_to_chains(Pitch p, Volume v, Duration d, Note n)
    {
        // add current values to chains
        pitch_chain.add_to_chain(p);
        volume_chain.add_to_chain(v);
        duration_chain.add_to_chain(d);
        note_chain.add_to_chain(n);
    }

    private void generate_rest(long start, long end)
    {
        // initialize new rest with just a duration (default values of
        // pitch and volume are encapsulated in the rest constructor)
        Duration rest_duration = new Duration(start, end);

        // only generate and add the rests if they last for a usable amount of time
        if (rest_duration.getTime() > 0.0)
        {
            Rest current_rest = new Rest(rest_duration);

            // add rest versions of each object to their respective chains
            pitch_chain.add_to_chain(new Pitch());
            volume_chain.add_to_chain(new Volume());
            duration_chain.add_to_chain(rest_duration);

            // add raw rest (since it's a subclass of Note) to the note chain
            note_chain.add_to_chain(current_rest);
        }

    }

    private void produce_song()
    {
        pitch_chain.print_chain();
        volume_chain.print_chain();
        duration_chain.print_chain();
        note_chain.print_chain();
    }

}
