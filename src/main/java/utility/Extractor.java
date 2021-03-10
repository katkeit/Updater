package utility;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Extractor
{
    public boolean unzipFile(String fileName)
    {
        try
        {
            Interface.getLabel().setText(Language.getExtractorMessages().get(0) + fileName + "...");
            ZipFile zipFile = new ZipFile(fileName);
            Enumeration<?> enu = zipFile.entries();

            while (enu.hasMoreElements())
            {
                ZipEntry zipEntry = (ZipEntry)enu.nextElement();
                String name = FileIO.getCalcPath() + zipEntry.getName();
                File file = new File(name);

                if (name.endsWith("/") && file.mkdirs())
                    continue;

                File parent = file.getParentFile();

                if (parent != null && parent.mkdirs())
                    continue;

                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);

                byte[] bytes = new byte[1024];
                int length;

                while ((length = is.read(bytes)) >= 0)
                {
                    Interface.getLabel().setText(Language.getExtractorMessages().get(0) + zipEntry.getName() + "...");
                    fos.write(bytes, 0, length);
                }

                is.close();
                fos.close();
                Interface.getLabel().setText(Language.getExtractorMessages().get(1) + fileName + "...");
            }

            zipFile.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
