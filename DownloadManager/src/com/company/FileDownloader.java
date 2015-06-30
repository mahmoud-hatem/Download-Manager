package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mahmoud-hatem on 6/25/15.
 */
public class FileDownloader extends Thread {

    private HttpURLConnection fileHttpURLConnection;
    private String filePath;
    private int fullSize;
    private int downloadedSize;
    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public int getFullSize() {
        return fullSize;
    }

    public int getDownloadedSize() {
        return downloadedSize;
    }


    public FileDownloader(URL fileURL, String filePath)
    {
        try {
            this.fileHttpURLConnection = (HttpURLConnection) fileURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.filePath = filePath;
        this.downloadedSize = 0;
        this.fullSize = -1;
        this.completed = false;
    }

    @Override
    public void run()
    {
        try {
            // Setting the range to continue downloading
            fileHttpURLConnection.setRequestProperty("Range", "bytes=" + this.downloadedSize + "-");

            if (this.fullSize == -1)
                this.fullSize = fileHttpURLConnection.getContentLength();

            FileOutputStream outputStream = new FileOutputStream(this.filePath, true);
            InputStream inputStream = fileHttpURLConnection.getInputStream();

            int data = inputStream.read();
            while (!this.isInterrupted() && data != -1)
            {
                outputStream.write(data);
                this.downloadedSize++;
                data = inputStream.read();
            }

            if (data == -1)
            {
                this.completed = true;
            }


            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        if (this.fullSize == -1)
        {
            String fileName = Utilities.getFileName(this.fileHttpURLConnection.getURL());
            return  fileName + " 0 MB 0 MB 0.00 %";
        }
        else
        {
            String fileName = Utilities.getFileName(this.fileHttpURLConnection.getURL());
            String size = this.fullSize/((float)1024*1024) + " MB";
            String amountDone = this.downloadedSize/((float)1024*1024) + " MB";
            String percentage = String.format(" %.2f %%",((double)this.downloadedSize)/this.fullSize);
            return  fileName + " " + size + " " + amountDone + " " + percentage;
        }

    }
}
