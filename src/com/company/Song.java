package com.company;

/*
 * Created by Garrett on 4/21/14.
 */

import javax.sound.midi.*;
import java.nio.ByteBuffer;
import com.company.Chainables.*;

public class Song {

    private static final String outputDirectory = "/Users/garrettparrish/Documents/Garrett/Projects/Composerator/Composerator";

    // midi sequence
    private Sequence midiSequence;

    // current midi track
    Track current_track;

    // chains
    private Chain pitch_chain;
    private Chain duration_chain;
    private Chain volume_chain;
    private Chain note_chain;

    // pulses per quarter note
    private float timebase;

    // constructor that takes four chains and a timebase
    public Song(Chain pc, Chain dc, Chain vc, Chain nc, float ppqn) {
        pitch_chain = pc;
        duration_chain = dc;
        volume_chain = vc;
        note_chain = nc;
        timebase = ppqn;

        print_song();
    }

    // prints out song to console (prints individual chains)
    public void print_song() {
        pitch_chain.print_chain();
        volume_chain.print_chain();
        duration_chain.print_chain();
        note_chain.print_chain();
    }

    // getters
    public Chain getVolume_chain()
    {
        return volume_chain;
    }

    public Chain getDuration_chain()
    {
        return duration_chain;
    }

    public Chain getPitch_chain()
    {
        return pitch_chain;
    }

    public float getTimebase()
    {
        return timebase;
    }


    /*
        Information on MIDI file formats adapted from:
        - http://www.fileformat.info/format/midi/corion.htm
        - http://www.midi.org/techspecs/midimessages.php
        - http://www.digitalpreservation.gov/formats/fdd/fdd000119.shtml
        - http://docs.oracle.com/javase/8/docs/api/javax/sound/midi/spi/MidiFileWriter.html
        - http://www.automatic-pilot.com/midifile.html

        GENERAL FORM OF A MIDI FILE

        - File header (14 constant bytes)
        - Track header (4 constant four bytes)
        - 4 bytes to indicate amount of track data (including footer) NOTE: big-endian
        - Track Data
            - Metadata events (tempo, key signature, time signature)
            - Performance events (notes, controller changes etc.)
        - Track footer (4 constant bytes)

        TIMEBASES

        - tempo of track is expressed in microseconds per quarter note
            - manually choose a multiplier: 16
                - longest note: 64 ticks
                - shortest note: 1 tick
            - set the timebase fairly low (fewer bits to represent each value)

        DELTAS

        - expressed in number of ticks
        - "delta = 0" means "do this as close as possible to previous event"
        - rest: turn note off - wait for N clicks - turn next note on again
        - have three notes playing at same time: turn 1st off at 16, then turn others off at 0 (since it's
          measured with respect to the last note)

        EVENT FORMAT

        0x90 --> (byte representing note) --> (byte representing strike velocity)

     */

    // static bits and values
    private static final int START_TICK = 0;
    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;
    private static final int SET_TEMPO = 0x51;
    private static final int SET_TRACK_NAME = 0x03;
    private static final int SET_END_OF_TRACK = 0x2F;

    // tick counter for going through song
    int current_tick = START_TICK;

    // desired output tempo (maybe have the user specify this) ?
    float output_tempo = 120;

    // converts the song back to a midi file
    public MidiFile toMidiFile() {
        try {
            // new sequence with song's num of ticks per beat  ****
            midiSequence = new Sequence(javax.sound.midi.Sequence.PPQ, (int) timebase);

            // create first track
            current_track = midiSequence.createTrack();

            ////////////////////////////////////////////////////////
            /////////////////////// HEADER /////////////////////////
            ////////////////////////////////////////////////////////

            // general MIDI configuration
            // have to do (byte) casts because Java has unsigned int problems
            byte[] b = {(byte) 0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte) 0xF7};
            SysexMessage sm = new SysexMessage();
            sm.setMessage(b, 6);

            MidiEvent me = new MidiEvent(sm, START_TICK);
            current_track.add(me);

            // calculate tempo in bytes
            float micro_per_minute = 60000000;
            int micro_per_pulse = (int) (micro_per_minute / output_tempo);
            byte[] bytes = ByteBuffer.allocate(4).putInt(micro_per_pulse).array();

            // three bytes represent number of microseconds per pulse
            byte[] bt = {bytes[1], bytes[2], bytes[3]};
            writeMetaEvent(SET_TEMPO, bt, 3, START_TICK);

            // set track name (meta event)
            String TrackName = "Composerator Track 1";
            writeMetaEvent(SET_TRACK_NAME, TrackName.getBytes(), TrackName.length(), START_TICK);

            // set omni on
            writeShortEvent(0xB0, 0x7D, 0x00, START_TICK);

            // set poly on
            writeShortEvent(0xB0, 0x7F, 0x00, START_TICK);

            // set instrument to Piano
            writeShortEvent(0xC0, 0x00, 0x00, START_TICK);

            byte[] bet = {}; // empty array
            writeMetaEvent(SET_END_OF_TRACK, bet, 0, current_tick);

            String Track2Name = "Composerator Track 2";
            writeMetaEvent(SET_TRACK_NAME, Track2Name.getBytes(), Track2Name.length(), START_TICK);

            ////////////////////////////////////////////////////////
            //////////////////////// BODY //////////////////////////
            ////////////////////////////////////////////////////////

            boolean ON = true;
            boolean OFF = false;

            // iterate through note chain and call note events on each note
            for (Object c : note_chain.getList())
            {
                // cast object to Note class
                noteEvent((Note) c);
            }

            ////////////////////////////////////////////////////////
            ////////////////////// FOOTER //////////////////////////
            ////////////////////////////////////////////////////////

            // set end of track
            writeMetaEvent(SET_END_OF_TRACK, bet, 0, current_tick);

        }
        catch (Exception e)
        {
            System.out.println("Exception caught " + e.toString());
        }

        // return a new MIDI file object with the constructed midi sequence
        return new MidiFile(midiSequence);
    }

    ////////////////////////////////////////////////////////
    //////////////// MIDI HELPER METHODS ///////////////////
    ////////////////////////////////////////////////////////

    // encapsulation method to map note to midi event
    private void noteEvent(Note n) {
        // maps string to a byte value
        Pitch p = n.getPitch();
        Volume v = n.getVolume();
        Duration d = n.getDuration();

        int ending_tick = current_tick + (int) d.getTick_length();
        int note = p.getMidi_id() - 1;
        int vel = v.getMidi_velocity();
        writeShortEvent(NOTE_ON, note, vel, current_tick);
        writeShortEvent(NOTE_OFF, note, vel, ending_tick);

        // set current tick to latest tick val
        current_tick = ending_tick;
    }

    // write a meta event to current track at certain tick
    private void writeMetaEvent(int id, byte[] val, int b3, int tick) {
        MetaMessage mt = new MetaMessage();
        try {
            mt.setMessage(id, val, b3);
        } catch (InvalidMidiDataException e) {
            System.out.println(e.toString());
        }
        MidiEvent me = new MidiEvent(mt, (long) tick);
        current_track.add(me);
    }

    // write a short message to current track at certain tick
    private void writeShortEvent(int id, int val, int vel, int tick) {
        ShortMessage sm = new ShortMessage();
        try {
            sm.setMessage(id, val, vel);
        } catch (InvalidMidiDataException e) {
            System.out.println(e.toString());
        }
        MidiEvent me = new MidiEvent(sm, (long) tick);
        current_track.add(me);
    }

    // appends a song to this current song
    public void appendSong(Song s)
    {
        // append all the individual chains within the song
        note_chain.appendChain(s.note_chain);
        pitch_chain.appendChain(s.pitch_chain);
        volume_chain.appendChain(s.volume_chain);
        duration_chain.appendChain(s.duration_chain);
    }
}