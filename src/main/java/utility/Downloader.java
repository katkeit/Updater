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

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader
{
    public boolean startDownload(String url, String fileName)
    {
        try
        {
            Interface.getLabel().setText(Language.getDownloaderMessages().get(0) + fileName + "...");
            ReadableByteChannel readChannel = Channels.newChannel((new URL(url)).openStream());
            FileOutputStream fileOS = new FileOutputStream(FileIO.getDownloadFolder() + fileName);
            fileOS.getChannel().transferFrom(readChannel, 0L, Long.MAX_VALUE);
            fileOS.close();

            Interface.getLabel().setText(Language.getDownloaderMessages().get(1) + fileName + "...");
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
