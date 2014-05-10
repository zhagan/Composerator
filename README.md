Composerator
============

To compile composerator, please open the Composerator directory that this readme file is placed in via the Java development of your choice: IntelliJ IDEA (which was used to develop this application), Eclipse, or any other. Once opened, run the the main function in the Main.java class via whatever method the IDE you choose specifies. Once run, the UI will startup and will be self-explanatory. 

Steps to run:

1. Upload MIDI files. We've included 4 sample midi files to test with Composerator. Feel free to use your own MIDI files but be aware that not all midi files were compiled and built with the same underlying structure and we do not have a robust enough midi decoder to read in any files. Feel free to try, but we don't take responsibility for your own input files not working properly. To upload, select the 'File 1' button which will open up a selection UI. You can also remove any files you upload by pressing the 'remove' button.

2. Specify the desired order of the markov analysis. For example, an order of 3 will result in the algorithm looking at 3 notes prior to a note when probabilistically determining the next note. This is an implicit direct relationship to the degree of differentiation between the songs. If the order is low, there is a higher probability that certain notes will be followed by the same or similar notes as it was in the input.

3. Specify the length of the output file in notes. Here, input the length of your desired output file.

4. Specify output file destination path. Click the 'destination path' button, which will open up a directory choosing window where you should choose which directory you wish to save your file in. 

5. Press composerate and try out your new composition!
