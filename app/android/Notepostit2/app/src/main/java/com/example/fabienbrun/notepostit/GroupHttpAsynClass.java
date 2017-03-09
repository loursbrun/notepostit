package com.example.fabienbrun.notepostit;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fabienbrun.notepostit.group.GroupPublic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class GroupHttpAsynClass extends AsyncTask<Integer, Float, String> {


    @Override
    protected String doInBackground(Integer... params) {
        //Log.i("TAG", "Result is empty...");

        StringBuilder result = null;
        //String path = "http://jsonplaceholder.typicode.com/photos";
        //String path = "http://api.androidhive.info/contacts/";
        //String path = "http://192.168.0.146:8080/projet.json";
        //String path = "http://172.20.10.2:8080/projet.json";
        //String path = "http://192.168.1.13:8080/GroupPublic.json";
        String path = "http://172.20.10.2:8080/GroupPublic.json";


        URL url;

        HttpURLConnection connection= null; try {
            url = new URL(path);
            connection = (HttpURLConnection)url.openConnection(); connection.setRequestProperty("Accept", "application/json"); connection.setRequestMethod("GET");
            connection.connect();
            int codeResponse = connection.getResponseCode();
            result = new StringBuilder();
            if( 200 <= codeResponse && codeResponse < 300 ){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); String line = null;
                while((line = br.readLine()) != null){
                    result.append(line);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        //Log.i("TAG", "Result is empty..." + result.toString());
        return result.toString();
    }



    @Override
    protected void onPostExecute(String result) { if(result == null || result.isEmpty()) {
        //Log.i("TAG", "Result is complete..." + result);





        return; }




        //Create the type “ArrayList<Album>” with a anonyme class
        Type listType = new TypeToken<ArrayList<GroupPublic>>() {}.getType(); //Convert result to objects
        ArrayList<GroupPublic> list = new Gson().fromJson(result, listType);
        //Updates the listView
        // albumAdapter.getAlbums().addAll(list);
        // albumAdapter.notifyDataSetChanged(); }catch(Exception e){
        // System.out.println("e " + e); }

        Log.i("TAG","Liste des groupes récupérée => " + list.toString());

        MySingleton.getInstance().setGroupsList(list);







            /*
            //Create the type “ArrayList<Album>” with a anonyme class
            Type listType = new TypeToken<ArrayList<Album>>() {}.getType(); //Convert result to objects
            List<Album> list = new Gson().fromJson(result, listType);
            //Updates the listView
            albumAdapter.getAlbums().addAll(list);
            albumAdapter.notifyDataSetChanged(); }catch(Exception e){
            System.out.println("e " + e); }
            */
    }


}
