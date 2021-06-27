package edu.neu.madcourse.numad21su_shailigandhi;


import android.util.Patterns;
import android.webkit.URLUtil;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class NetworkUtil {

    public static String checkValid(String url) {
        if (URLUtil.isValidUrl(url) || Patterns.WEB_URL.matcher(url).matches()) {
            if(!(url.startsWith("http://") || url.startsWith("https://"))){
                return "https://" + url;
            }
            return url;
        }
        return "Invalid Input";
    }

    public static String responseToString(InputStream inpStream){
        StringBuilder strBuild=new StringBuilder();
        BufferedReader buffReader=new BufferedReader(new InputStreamReader(inpStream));
        String line;
        try {
            while((line=buffReader.readLine())!=null){
                strBuild.append(line);
            }
            buffReader.close();
            return strBuild.toString().replace(",", ",\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getResponseFromURL(URL url) {
        HttpURLConnection connection;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            return NetworkUtil.responseToString(inputStream);
        }
        return "No Result";
    }


}