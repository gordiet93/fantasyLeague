package com.example.fantasyLeague.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helper {

    private static final String URLBASE = "https://soccer.sportmonks.com/api/v2.0/";
    private static final String APITOKEN = "api_token=jbGDcAS7dcekbiqJn9HKU6pTftzFXl0dxVxQkVktGvjw1cJudCnS7p9U5pP2";

    public static String callURL(String url1) throws IOException {
        String contentString;
        URL url = new URL(URLBASE + url1 + APITOKEN);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        contentString = content.toString();

        return contentString;
    }
}
