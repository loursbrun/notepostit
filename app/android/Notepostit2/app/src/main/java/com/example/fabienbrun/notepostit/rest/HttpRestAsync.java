package com.example.fabienbrun.notepostit.rest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRestAsync extends AsyncTask<Void, Void, String> {

    public interface OnHttpRestAsyncComplete {
        void onComplete(String result);
    }

    private static final String TAG = "HttpRestAsync";

    private OnHttpRestAsyncComplete listener;
    private String path;
    private String method = "GET";
    private String body;

    public HttpRestAsync(OnHttpRestAsyncComplete listener,
                         String path,
                         String method,
                         String body) {
        this.listener = listener;
        this.path = path;
        this.method = method;
        this.body = body;
    }

    public HttpRestAsync(OnHttpRestAsyncComplete listener,
                         String path) {
        this(listener, path, "GET", "");
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();

        HttpURLConnection connection= null;
        try {
            URL url = new URL(this.path);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestMethod(this.method);

            if (this.body != null && !this.body.isEmpty()) {
                connection.setDoOutput(true);
            }

            connection.connect();

            if (this.body != null && !this.body.isEmpty()) {
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                osw.write(this.body);
                osw.flush();
                osw.close();
            }

            int codeResponse = connection.getResponseCode();
            if (200 <= codeResponse && codeResponse < 300) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while((line = br.readLine()) != null){
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
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null || result.isEmpty()) {
            Log.d(TAG, "Result  is empty...");
            return;
        }

        listener.onComplete(result);
    }
}
