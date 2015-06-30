package com.company;

import java.net.URL;

/**
 * Created by mahmoud-hatem on 6/25/15.
 */
public class Utilities {

    public static String getDirectoryPath()
    {
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            return "C:/Users/" + System.getProperty("user.name");
        else
            return System.getProperty("user.home");
    }

    public static String getFileName(URL url)
    {
        return url.getPath().substring(url.getPath().lastIndexOf('/') + 1);
    }
}
