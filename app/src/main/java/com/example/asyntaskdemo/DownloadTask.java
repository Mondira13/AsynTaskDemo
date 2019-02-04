package com.example.asyntaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class DownloadTask extends AsyncTask<Void,Integer,String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;

    public DownloadTask(Context context, TextView textView, Button button) {
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in progress");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(Void... voids) {
        int i = 10;
        synchronized (this){
            while (i<100){
                try {
                    publishProgress(i);
                    wait(2000);
                    i=i+10;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        publishProgress(i);
        return "Download complete...";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download in progress....");
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }
}
