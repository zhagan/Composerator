package com.company.Input;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Arrays;


/*
 * Created by Garrett on 4/21/14.
 */

// Represents an audio file for processing into input

// This class is not used in this version of composerator
// the purpose of this class was to be able to deduce a midi sequence
// from a wav audio file (by taking the fft and comparing to a
// frequency spectrum etc.)
public class Audio_File {

    private byte[] fileBytes;
    private Path filePath;
    private File file;
    private AudioInputStream audioInputStream;

    // Constructor to create an audio file object
    public Audio_File(String path) {

        // store file path
        filePath = Paths.get(path);

        // get file from path
        file = new File(path);

        try
        {
//            audioInputStream = AudioSystem.getAudioInputStream();

            // read bytes from file and print out
            fileBytes = Files.readAllBytes(filePath);

            System.out.print("File successfully read.");

            // print out for debugging
            System.out.println(Arrays.toString(fileBytes));

        }
        catch (IOException e)
        {
            System.out.print(e.toString());
        }
    }

    public void playFile() {

        AudioInputStream audioInputStream = null;

        // read the audio file into an input stream
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        }
        catch(IOException e)
        {
            System.out.print(e.toString());
        }
        catch(UnsupportedAudioFileException e)
        {
            System.out.print(e.toString());
        }
    }
}
