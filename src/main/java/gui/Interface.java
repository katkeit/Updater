package gui;

/*
    @project Updater
    @author Katelyn Eitel
    Created on 2/20/2021.

    Copyright (C) 2021  Katelyn Eitel

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
