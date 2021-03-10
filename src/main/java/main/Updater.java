package main;

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
import io.Language;
import gui.Interface;
import utility.Downloader;
import utility.Extractor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Updater
{
    public static void main(String[] args)
    {
        FileIO fileIO = new FileIO();
        fileIO.readFile("config/settings.txt");
        fileIO.readFile("config/preferences.txt");
        fileIO.readFile("download/version.txt");

        Interface gui = new Interface();
        gui.createGUI();

        Language language = new Language();
        language.setLanguage(FileIO.getPrefLanguage());

        if (!FileIO.getAppVersion().matches(FileIO.getUpdateVersion()))
        {
            String[] options = { Language.getUpdaterMessages().get(0) };
            int input = JOptionPane.showOptionDialog(null, Language.getUpdaterMessages().get(2) + FileIO.getUpdateVersion() + " " + Language.getUpdaterMessages().get(3) + "\n" + Language.getUpdaterMessages().get(4) + FileIO.getUpdatePage() + ").", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (input == 0)
                try
                {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                        Desktop.getDesktop().browse(new URI(FileIO.getUpdatePage()));

                    Runtime.getRuntime().exit(0);
                }
                catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }

            Runtime.getRuntime().exit(0);
        }

        Downloader downloader = new Downloader();

        if (downloader.startDownload(FileIO.getDownloadLink(), FileIO.getUpdateZip()))
        {
            Extractor extractor = new Extractor();

            if (extractor.unzipFile(FileIO.getDownloadFolder() + FileIO.getUpdateZip()))
            {
                try {
                    Runtime.getRuntime().exec("java -jar " + FileIO.getMainAppPath() + FileIO.getMainAppName());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.exit(0);
            }
        }

        fileIO.deleteFile(FileIO.getDownloadFolder() + FileIO.getUpdateZip());
        fileIO.deleteFile(FileIO.getDownloadFolder() + "version.txt");
    }
}
