package com.nvk.loader2;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NetWork {
    public static final String BASE ="http://192.168.43.218:8000/public/api/";

    public static String connect(String uri, String method, HashMap<String,String> params){
        Uri.Builder builder = Uri.parse(BASE+uri).buildUpon();
        for (Map.Entry<String,String> param: params.entrySet()) {
            builder.appendQueryParameter(param.getKey(),param.getValue());
        }
        Uri uriBuilt = builder.build();
        String json =null;
        HttpURLConnection connection=null;
        try {
            URL url = new URL(uriBuilt.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine())!= null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            json = stringBuilder.toString();
            reader.close();
            inputStream.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return json;
    }
}
