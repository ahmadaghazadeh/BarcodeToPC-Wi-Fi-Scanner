package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

import android.os.AsyncTask;


public class SimpleAsyncTask extends AsyncTask<Object, Object, Object> {
    private RunnableMethod runDo;
    private RunnableMethod runPre;
    private RunnableIn runPost;

    public SimpleAsyncTask(RunnableMethod runPre,
                           RunnableMethod runDo, RunnableIn runPost) {
        this.runPre = runPre;
        this.runDo = runDo;
        this.runPost = runPost;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (runPre != null) {
            runPre.run(null, null);
        }
    }

    @Override
    protected Object doInBackground(Object... obj) {
        Object objReturn = new Object();
        if (runDo != null) {
            OnProgressUpdate onProgressUpdate = new OnProgressUpdate() {

                @Override
                public void onProgressUpdate(String title) {
                    publishProgress(title);
                }

                @Override
                public void onProgressUpdate(String title, String message) {
                    publishProgress(title, message);
                }
            };
            objReturn = runDo.run(null, onProgressUpdate);
        }
        return objReturn;
    }

    @Override
    protected void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        if (runPost != null) {
            runPost.run(obj);
        }
    }


}
