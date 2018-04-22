package com.example.wlaza.smart_przodek.FileManagement;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader extends AsyncTask<URL, Void, Void> {
    private String outputFileName;
    private String directory;
    public FileDownloader(String dir, String output) {
        super();
        directory = dir;
        outputFileName = output;
    }

    public Void doInBackground(URL...urls) {
        try {
            HttpURLConnection c = (HttpURLConnection) urls[0].openConnection();//Open Url Connection
            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
            c.connect();//connect the URL Connection
            //If Connection response is not OK then show Logs
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("sciaganie", "Server returned HTTP " + c.getResponseCode()
                        + " " + c.getResponseMessage());

            }

            File outputDirectory = new File(directory,"materialy");


            //If File is not present create directory
            if (!outputDirectory.exists()) {
                outputDirectory.mkdir();
                Log.e("output", "Directory Created.");
            }

            File outputFile = new File(outputDirectory, outputFileName);//Create Output file in Main File

            //Create New File if not present
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.e("output", "File Created");
            }

            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

            InputStream is = c.getInputStream();//Get InputStream for connection

            byte[] buffer = new byte[1024];//Set buffer type
            int len1 = 0;//init length
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);//Write new file
            }

            //Close all connection after doing task
            fos.close();
            is.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
