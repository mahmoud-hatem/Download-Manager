package com.company;

import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String file1 = "https://scontent-cdg2-1.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11060912_10155532313255398_5586992235359410933_n.jpg?oh=6692ecf5764c0d6c3c44fc2757fdb406&oe=55CB9264";
        String file2 = "http://www.freesoft.org/CIE/RFC/1321/mddriver.c";
        DownloadManager.getInstance().addDownload(file1);
        DownloadManager.getInstance().addDownload(file2);

        DownloadManager.getInstance().startDownload(0);
        DownloadManager.getInstance().startDownload(1);

        String old = "";
        while (true)
        {
            if (!old.equals(DownloadManager.getInstance().toString())) {
                System.out.println(DownloadManager.getInstance());
                old = DownloadManager.getInstance().toString();
            }
        }

//        Scanner scanner = new Scanner(System.in);
//        String urlString = scanner.nextLine();
//        try {
//            URL url = new URL(urlString);
//
//
//            URLConnection urlConnection = url.openConnection();
//            HttpURLConnection httpURLConnection = null;
//
//            if (urlConnection instanceof HttpURLConnection)
//            {
//                httpURLConnection = (HttpURLConnection)urlConnection;
//            }
//           // else if (urlConnection instanceof FtpURLConnection)
//            else
//            {
//                System.out.println("Not Http URL");
//                return;
//            }
//
//
//            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
//
//            long size = httpURLConnection.getContentLength();
//
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//
//            String line;
//            while ((line = reader.readLine()) != null)
//            {
//                System.out.println(line);
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
