package io;

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

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Language
{
    // System messages. Check lang/interface_en.txt.
    private static final ArrayList<String> updaterMessages = new ArrayList<>();
    private static final ArrayList<String> downloaderMessages = new ArrayList<>();
    private static final ArrayList<String> extractorMessages = new ArrayList<>();

    /**
     * Sets the application's language preference.
     * @param language The abbreviation of the preferred language (EN, ES, PT).
     */
    public void setLanguage(String language)
    {
        switch (language) {
            case "EN" -> loadLanguage(FileIO.getEnInterface());
            case "ES" -> loadLanguage(FileIO.getEsInterface());
            case "PT" -> loadLanguage(FileIO.getPtInterface());
        }
    }

    /**
     * Loads the preferred language file.
     * @param fileName The name of the file.
     */
    private void loadLanguage(String fileName)
    {
        try
        {
            // Wini reads ini files. Check pom.
            Wini ini = new Wini(new File(fileName));

            updaterMessages.add(ini.get("UPDATER", "001"));
            updaterMessages.add(ini.get("UPDATER", "002"));
            updaterMessages.add(ini.get("UPDATER", "003"));
            updaterMessages.add(ini.get("UPDATER", "004"));
            updaterMessages.add(ini.get("UPDATER", "005"));
            updaterMessages.add(ini.get("UPDATER", "006"));
            downloaderMessages.add(ini.get("DOWNLOADER", "001"));
            downloaderMessages.add(ini.get("DOWNLOADER", "002"));
            extractorMessages.add(ini.get("EXTRACTOR", "001"));
            extractorMessages.add(ini.get("EXTRACTOR", "002"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public static ArrayList<String> getUpdaterMessages() {
        return updaterMessages;
    }

    public static ArrayList<String> getDownloaderMessages() {
        return downloaderMessages;
    }

    public static ArrayList<String> getExtractorMessages() {
        return extractorMessages;
    }
}
