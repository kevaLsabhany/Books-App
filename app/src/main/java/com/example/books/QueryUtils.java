package com.example.books;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

    public static ArrayList<Book> extractDataFromApi(String urlString) {
        ArrayList<Book> books = new ArrayList<>();
        String jsonResponse = "";
        if (urlString == "" || urlString == null)
            return books;
        URL url = createUrl(urlString);
        jsonResponse = makeHttpRequest(url);

        try {
            books = getDataFromJson(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("boks",books.toString());
        return books;
    }

    public static URL createUrl(String urlString)  {
        URL url = null;
        try {
           url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makeHttpRequest(URL url) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Book> getDataFromJson(String jsonResponse) throws JSONException {
        ArrayList<Book> books = new ArrayList<>();
        Book b;
        if (jsonResponse != null) {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray items = root.optJSONArray("items");
            if (items == null)
                return  books;
            for (int i=0; i<items.length(); i++) {
                b = new Book();
                JSONObject item = items.optJSONObject(i);
                JSONObject volumeInfo = item.optJSONObject("volumeInfo");
                b.setmBookName(volumeInfo.optString("title"));
                JSONArray authors = volumeInfo.optJSONArray("authors");
                if(authors == null)
                    b.setmAuthorName("Unknown");
                else
                    b.setmAuthorName(authors.getString(0));
                b.setmPublisher(volumeInfo.optString("publisher"));
                b.setmPages(volumeInfo.optInt("pageCount"));
                b.setmSelfUrl(volumeInfo.getString("infoLink"));
                JSONObject imageLinks = volumeInfo.optJSONObject("imageLinks");
                if (imageLinks == null)
                    b.setmImageUrl(null);
                else
                    b.setmImageUrl(imageLinks.optString("thumbnail"));
                books.add(b);
            }
            Log.i("books", books.toString());
        }
        return books;
    }
}
