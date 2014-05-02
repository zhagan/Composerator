// Copyright info
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.company.UI;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */


// this class is adapted from one found online: http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
// this class is itself a JPanel but has many subclasses JPanel components
public class FileChooser extends JFrame implements ActionListener
{
    static private final String newline = "\n";
    JButton chooseFile1, chooseFile2, chooseFile3;
    JTextArea file1Log, file2Log, file3Log;
    JScrollPane log1ScrollPane, log2ScrollPane, log3ScrollPane;

    JFileChooser fc;

    public FileChooser()
    {
        super("TEST");

        // create a file chooser
        fc = new JFileChooser();

        // FOR EACH INPUT FILE - CREATE A CHOOSE BUTTON AND A TEST FIELD SPECIFYING STATUS

        ///////////////////////////////////////////////////////////////
        ///////////////////// INSTRUCTIONS ////////////////////////////
        ///////////////////////////////////////////////////////////////
/*
        JTextArea instructions = new JTextArea(1,45);
        instructions.setMargin(new Insets(5,5,5,5));
        instructions.setEditable(false);
        instructions.setText("Welcome to Composerator! Please select up to three MIDI files that you wish to Composerate!");

        JPanel instructionsPanel = new JPanel();
        instructionsPanel.add(instructions);
        instructionsPanel.setPreferredSize(new Dimension(150,10));
        add(instructionsPanel);

        ///////////////////////////////////////////////////////////////
        ///////////////////// INPUT FILE 1 ////////////////////////////
        ///////////////////////////////////////////////////////////////

        // create three choose file buttons (one for each optional midi file)
        chooseFile1 = new JButton("File 1");
        chooseFile1.addActionListener(this);

        // text box to print status updates
        file1Log = new JTextArea(1,40);
        file1Log.setMargin(new Insets(5,5,5,5));
        file1Log.setEditable(false);
        log1ScrollPane = new JScrollPane(file1Log);

        // create panel and add buttons and log
        JPanel chooseFile1Panel = new JPanel();
        chooseFile1Panel.add(chooseFile1);
        chooseFile1Panel.add(log1ScrollPane);
//        chooseFile1Panel.setPreferredSize(new Dimension(150,200));
        add(chooseFile1Panel);


        ///////////////////////////////////////////////////////////////
        ///////////////////// INPUT FILE 2 ////////////////////////////
        ///////////////////////////////////////////////////////////////

        // create three choose file buttons (one for each optional midi file)
        chooseFile2 = new JButton("File 2");
        chooseFile2.addActionListener(this);

        // text box to print status updates
        file2Log = new JTextArea(1,40);
        file2Log.setMargin(new Insets(5,5,5,5));
        file2Log.setEditable(false);
        log2ScrollPane = new JScrollPane(file2Log);

        // create panel and add buttons and log
        JPanel chooseFile2Panel = new JPanel();
        chooseFile2Panel.add(chooseFile2);
        chooseFile2Panel.add(log2ScrollPane);
        add(chooseFile2Panel);

        ///////////////////////////////////////////////////////////////
        ///////////////////// INPUT FILE 2 ////////////////////////////
        ///////////////////////////////////////////////////////////////

        // create three choose file buttons (one for each optional midi file)
        chooseFile3 = new JButton("File 3");
        chooseFile3.addActionListener(this);

        // text box to print status updates
        file3Log = new JTextArea(1,40);
        file3Log.setMargin(new Insets(5,5,5,5));
        file3Log.setEditable(false);
        log3ScrollPane = new JScrollPane(file3Log);

        // create panel and add buttons and log
        JPanel chooseFile3Panel = new JPanel();
        chooseFile3Panel.add(chooseFile3);
        chooseFile3Panel.add(log3ScrollPane);
        add(chooseFile3Panel);

        ///////////////////////////////////////////////////////////////
        //////////////////////// CONTROL //////////////////////////////
        ///////////////////////////////////////////////////////////////
*/
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == chooseFile1)
        {
            int returnVal = fc.showOpenDialog(FileChooser.this);

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                System.out.println("File 1: " + file.getAbsolutePath());
//                log.append("File 1: " + file.getAbsolutePath());
                // pass file path to program
            }
            else
            {
//                log.append("Open command cancelled by user." + newline);
            }
            file1Log.setCaretPosition(file1Log.getDocument().getLength());
        }
        else if (e.getSource() == chooseFile2)
        {
            int returnVal = fc.showSaveDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                System.out.println("File 2: " + file.getAbsolutePath());
//                log.append("File 2: " + file.getAbsolutePath());
            }
            else
            {
//                log.append("Save command cancelled by user." + newline);
            }
//            log.setCaretPosition(log.getDocument().getLength());
        }
        else if (e.getSource() == chooseFile3)
        {
            int returnVal = fc.showSaveDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                System.out.println("File 3: " + file.getAbsolutePath());
  //              log.append("File 3: " + file.getAbsolutePath());
            }
            else
            {
//                log.append("Save command cancelled by user." + newline);
            }
//            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    private void createUI(final Container pane)
    {
        JTextArea instructions = new JTextArea(1,45);
        instructions.setMargin(new Insets(5,5,5,5));
        instructions.setEditable(false);
        instructions.setText("Welcome to Composerator! Please select up to three MIDI files that you wish to Composerate!");

        JPanel instructionsPanel = new JPanel();
        instructionsPanel.add(instructions);
        instructionsPanel.setPreferredSize(new Dimension(150,10));
        pane.add(instructionsPanel, BorderLayout.NORTH);

        ///////////////////////////////////////////////////////////////
        ///////////////////// INPUT FILE 1 ////////////////////////////
        ///////////////////////////////////////////////////////////////

        // create three choose file buttons (one for each optional midi file)
        chooseFile1 = new JButton("File 1");
        chooseFile1.addActionListener(this);

        // text box to print status updates
        file1Log = new JTextArea(1,40);
        file1Log.setMargin(new Insets(5,5,5,5));
        file1Log.setEditable(false);
        log1ScrollPane = new JScrollPane(file1Log);

        // create panel and add buttons and log
        JPanel chooseFile1Panel = new JPanel();
        chooseFile1Panel.add(chooseFile1);
        chooseFile1Panel.add(log1ScrollPane);
//        chooseFile1Panel.setPreferredSize(new Dimension(150,200));
        pane.add(chooseFile1Panel);




    }

    private void createAndShowGUI()
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Composerator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileChooser());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        createUI(frame.getContentPane());
    }

    public void main()
    {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}