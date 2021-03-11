package utility;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Extractor
{
    /**
     * Unzips a zip file in the main application's directory.
     * @param fileName The name of the zip file.
     * @return Return true or false depending on the success of unzipping the file.
     */
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
                String name = FileIO.getMainAppPath() + zipEntry.getName();
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
