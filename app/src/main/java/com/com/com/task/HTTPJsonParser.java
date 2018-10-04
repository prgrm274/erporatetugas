package com.com.com.task;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class HTTPJsonParser {

    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";
    private HttpURLConnection urlConnection = null;

    public JSONObject makeHttpRequest(
            String url,
            String method,
            Map<String, String> params) {

        try {
            Uri.Builder builder = new Uri.Builder();
            URL urlObj;
            String encodedParams = "";

            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
            }

            if (builder.build().getEncodedQuery() != null) {
                encodedParams = builder.build().getEncodedQuery();

            }

            if ("GET".equals(method)) {
                url = url + "?" + encodedParams;
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);

            } else {
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(encodedParams.getBytes().length));
                urlConnection.getOutputStream().write(encodedParams.getBytes());
            }

            //Connect to the server
            urlConnection.connect();
            //Read the response
            is = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = "";

            //Parse the response
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            json = sb.toString();
            //Jum 28-09-18 202227 tag
            Log.v("TAG", "json = sb.toString : " + json);
            //Convert the response to JSON Object
            jObj = new JSONObject(json);
            //Jum 28-09-18 202356 tag
            Log.v("TAG", "jObj = new JSONObject(json) : " + jObj);

        } catch (UnsupportedEncodingException | ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (Exception e) {
            Log.e("Exception", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jObj;
    }
}
