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

import javax.swing.*;
import java.awt.*;

public class Interface
{
    private static JFrame frame;
    private static JLabel label;
    private static JPanel panel = new JPanel();

    public void createGUI()
    {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 100));
        frame.setTitle("Updater v" + FileIO.getAppVersion());
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FileIO.getAppIcon()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        label = new JLabel("");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.ipady = 15;
        gbc.anchor = 10;

        panel.add(label, gbc);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static JLabel getLabel() {
        return label;
    }
}
