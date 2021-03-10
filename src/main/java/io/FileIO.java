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

public class FileIO
{
    private Wini ini = null;
    private static String appVersion;
    private static String appIcon;
    private static String mainAppPath;
    private static String mainAppName;
    private static String enInterface;
    private static String esInterface;
    private static String ptInterface;
    private static String downloadFolder;
    private static String updateZip;
    private static String prefLanguage;
    private static String downloadLink;
    private static String updateVersion;
    private static String updatePage;

    public void readFile(String fileName)
    {
        if (fileName.contains("settings"))
            loadSettings(fileName);
        else if (fileName.contains("preferences"))
            loadPreferences(fileName);
        else if (fileName.contains("version"))
            loadVersion(fileName);
    }

    public void deleteFile(String fileName)
    {
        File file = new File(fileName);

            do {
                if(file.delete())
                    break;
            } while(!file.delete());
    }

    private void loadSettings(String fileName)
    {
        try
        {
            this.ini = new Wini(new File(fileName));

            appVersion = this.ini.get("VERSION", "APP");
            appIcon = this.ini.get("GENERAL", "ICON");
            mainAppPath = this.ini.get("GENERAL", "MAIN_APP_PATH");
            mainAppName = this.ini.get("GENERAL", "MAIN_APP_NAME");
            enInterface = this.ini.get("INTERFACE", "ENGLISH");
            esInterface = this.ini.get("INTERFACE", "SPANISH");
            ptInterface = this.ini.get("INTERFACE", "PORTUGUESE");
            downloadFolder = this.ini.get("DOWNLOAD", "FOLDER");
            updateZip = this.ini.get("DOWNLOAD", "UPDATE_ZIP");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPreferences(String fileName)
    {
        try
        {
            this.ini = new Wini(new File(fileName));
            prefLanguage = this.ini.get("PREFERENCES", "LANGUAGE");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadVersion(String fileName)
    {
        try
        {
            this.ini = new Wini(new File(fileName));

            downloadLink = this.ini.get("CALCULATOR", "DOWNLOAD");
            updateVersion = this.ini.get("UPDATER", "VERSION");
            updatePage = this.ini.get("UPDATER", "PAGE");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static String getAppIcon() {
        return appIcon;
    }

    public static String getMainAppPath() {
        return mainAppPath;
    }

    public static String getMainAppName() {
        return mainAppName;
    }

    public static String getEnInterface() {
        return enInterface;
    }

    public static String getEsInterface() {
        return esInterface;
    }

    public static String getPtInterface() {
        return ptInterface;
    }

    public static String getPrefLanguage() {
        return prefLanguage;
    }

    public static String getDownloadFolder() {
        return downloadFolder;
    }

    public static String getUpdateZip() {
        return updateZip;
    }

    public static String getDownloadLink() {
        return downloadLink;
    }

    public static String getUpdateVersion() {
        return updateVersion;
    }

    public static String getUpdatePage() {
        return updatePage;
    }
}
