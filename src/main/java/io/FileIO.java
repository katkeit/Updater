package io;

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
