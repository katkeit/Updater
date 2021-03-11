package gui;

/*
    @project Updater
    @author Katelyn Eitel
    Created on 2/20/2021.

    Copyright (C) 2021  Katelyn Eitel

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/

import io.FileIO;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Interface
{
    private static JLabel label;

    /**
     * Creates the graphical user interface for the application.
     */
    public void createGUI()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Set the preferred size.
        frame.setPreferredSize(new Dimension(400, 100));
        // Set the title.
        frame.setTitle("Updater v" + FileIO.getAppVersion());
        // Set the application's icon.
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FileIO.getAppIcon()));
        // Set the application's default closing operation to exit on close when pressing the (X) button in top right.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Setting the panel's layout to Gridbag.
        panel.setLayout(new GridBagLayout());
        // Create the Gridbag constraints.
        GridBagConstraints gbc = new GridBagConstraints();

        // Create label.
        label = new JLabel("");
        // Setup the Gridbag constraints for the label.
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.ipady = 15;
        gbc.anchor = 10;

        // Add label and it's constraints to the panel.
        panel.add(label, gbc);
        // Add panel to frame.
        frame.add(panel);
        // Pack the frame and make frame visible.
        frame.pack();
        frame.setVisible(true);
    }

    // Getter
    public static JLabel getLabel() {
        return label;
    }
}