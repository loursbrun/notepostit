package com.example.fabienbrun.notepostit;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class UserRequestHttpProjet extends AsyncTask<Void, Void, String> {


    @Override
    protected String doInBackground(Void... params) {
        //Log.i("TAG", "Result is empty...");

        StringBuilder result = null;
        //String path = "http://jsonplaceholder.typicode.com/photos";
        //String path = "http://api.androidhive.info/contacts/";
        String path = "http://172.20.10.2:8080/user.json";


        URL url;

        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.connect();
            int codeResponse = connection.getResponseCode();
            result = new StringBuilder();
            if (200 <= codeResponse && codeResponse < 300) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        //Log.i("TAG", "Result is empty...hohoho" + result.toString());



        /*
        ArrayList<Projet> projetList = new ArrayList<>();
        projetList = new Gson().fromJson(result.toString(), new TypeToken<ArrayList<Projet>>() {}.getType());
        MySingleton.getInstance().setProjetList(projetList);
        Log.i("TAG","projetList is :" + projetList.size());

        */


        return result.toString();
    }


    @Override
    protected void onPostExecute(String result) {
        if (result == null || result.isEmpty()) {
            Log.i("TAG", "Result is none...");


            return;
        }


    }

}

