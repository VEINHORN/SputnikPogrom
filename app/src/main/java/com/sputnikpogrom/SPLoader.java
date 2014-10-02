package com.sputnikpogrom;

import android.os.AsyncTask;
import android.widget.ListView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class SPLoader extends AsyncTask<String, String, ShortArticlesContainer> {
    private ListView articlesListView;

    public SPLoader(ListView articlesListView) {
        this.articlesListView = articlesListView;
    }

    @Override
    protected ShortArticlesContainer doInBackground(String... args) {
        return new SPFetcher().getArticles();
    }

    @Override
    protected void onPostExecute(ShortArticlesContainer shortArticles) {
        
    }
}
