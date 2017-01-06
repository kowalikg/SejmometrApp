package com.company;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gabrysia on 06.01.2017.
 */
public class Downloader {
    private String jsonUrl;
    private String jsonResult = "";

    public Downloader(String jsonUrl){
        this.jsonUrl = jsonUrl;
    }
    public void download(){
        System.out.println(jsonUrl);
        URL url = null;
        try {
            url = new URL(jsonUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line="";
            while((line = bufferedReader.readLine()) != null){
                jsonResult += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJsonResult(){
        return jsonResult;
    }

}
