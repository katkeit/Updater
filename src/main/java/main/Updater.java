package main;

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
import io.Language;
import gui.Interface;
import utility.Downloader;
import utility.Extractor;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Updater
{
    public static void main(String[] args)
    {
        // Read the settings, preferences, and version information.
        FileIO fileIO = new FileIO();
        fileIO.readFile("config/settings.txt");
        fileIO.readFile("config/preferences.txt");
        fileIO.readFile("download/version.txt");

        // Create the graphical user interface.
        Interface gui = new Interface();
        gui.createGUI();

        // Set the user's preferred language.
        Language language = new Language();
        language.setLanguage(FileIO.getPrefLanguage());

        // If the Updater's version doesn't match the Updater's update version from version.txt.
        if (!FileIO.getAppVersion().matches(FileIO.getUpdateVersion()))
        {
            // The array of options for the JOptionPane set in the user's preferred language.
            String[] options = { Language.getUpdaterMessages().get(0) };
            // Get the feedback from the user (0 or 1) after setting up the JOptionPane.
            int input = JOptionPane.showOptionDialog(null, Language.getUpdaterMessages().get(2) + FileIO.getUpdateVersion() + " " + Language.getUpdaterMessages().get(3) + "\n" + Language.getUpdaterMessages().get(4) + FileIO.getUpdatePage() + ").", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // If user accepts to update the Updater.
            if (input == 0)
                try
                {
                    // If user is on a computer, then get the user's preferred browser.
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                        // Open the update page in the preferred browser.
                        Desktop.getDesktop().browse(new URI(FileIO.getUpdatePage()));

                    // Safely exit the application - allow the user to update the Updater.
                    Runtime.getRuntime().exit(0);
                }
                catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }

            // If user refuses to update the application, then exit.
            Runtime.getRuntime().exit(0);
        }

        Downloader downloader = new Downloader();

        // If the Updater doesn't need to update, then update the main application by downloading the zip file.
        if (downloader.startDownload(FileIO.getDownloadLink(), FileIO.getUpdateZip()))
        {
            Extractor extractor = new Extractor();

            // Try to successfully unzip the zip and install the update.
            if (extractor.unzipFile(FileIO.getDownloadFolder() + FileIO.getUpdateZip()))
            {
                try {
                    // If successful, then launch the main application.
                    Runtime.getRuntime().exec("java -jar " + FileIO.getMainAppPath() + FileIO.getMainAppName());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                Runtime.getRuntime().exit(0);
            }
        }

        // Delete the version.txt and the update zip.
        fileIO.deleteFile(FileIO.getDownloadFolder() + FileIO.getUpdateZip());
        fileIO.deleteFile(FileIO.getDownloadFolder() + "version.txt");
    }
}
