package com.company;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by mahmoud-hatem on 6/25/15.
 */
public class DownloadManager {
    private static DownloadManager ourInstance = new DownloadManager();

    private String directoryPath;
    private Vector<FileDownloader> fileDownloaders;

    public static DownloadManager getInstance() {
        return ourInstance;
    }

    private DownloadManager() {
        this.fileDownloaders = new Vector<>();
        this.directoryPath = Utilities.getDirectoryPath() + "/Downloads/";
    }

    public void addDownload(String urlString)
    {
        try {

            URL url = new URL(urlString);
            String filePath = this.directoryPath + Utilities.getFileName(url);
            FileDownloader fileDownloader = new FileDownloader(url, filePath);

            this.fileDownloaders.add(fileDownloader);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void startDownload(int index)
    {
        if (!this.fileDownloaders.get(index).isCompleted())
            this.fileDownloaders.get(index).start();
    }

    public void pauseDownload(int index)
    {
        if (!this.fileDownloaders.get(index).isCompleted() && this.fileDownloaders.get(index).isAlive())
            this.fileDownloaders.get(index).interrupt();
    }

    public boolean isDownloadCompleted(int index)
    {
        return this.fileDownloaders.get(index).isCompleted();
    }

    @Override
    public String toString()
    {
        String string = "";
        for (int i = 0; i < this.fileDownloaders.size(); ++i)
            string += ((i+1)+ "- " + this.fileDownloaders.get(i)) + "\n";

        return string;
    }
}
