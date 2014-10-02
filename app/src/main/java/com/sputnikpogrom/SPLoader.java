package com.sputnikpogrom;

import android.os.AsyncTask;
import android.widget.ListView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class SPLoader extends AsyncTask<String, String, ShortArticlesContainer> {
    private ListView articlesListView;
    private ShortArticlesContainer shortArticlesContainer;
    private ShortArticlesAdapter shortArticlesAdapter;

    public SPLoader(ListView articlesListView, ShortArticlesContainer shortArticlesContainer, ShortArticlesAdapter shortArticlesAdapter) {
        this.articlesListView = articlesListView;
        this.shortArticlesContainer = shortArticlesContainer;
        this.shortArticlesAdapter = shortArticlesAdapter;
    }

    @Override
    protected ShortArticlesContainer doInBackground(String... args) {
        return new SPFetcher().getArticles();
    }

    @Override
    protected void onPostExecute(ShortArticlesContainer shortArticles) {
        for(int i = 0; i < shortArticles.size(); i++) {
            ShortArticle shortArticle = new ShortArticle();
            shortArticle.setTitle(shortArticles.getShortArticle(i).getTitle());
            shortArticle.setUrl(shortArticles.getShortArticle(i).getUrl());
            shortArticlesContainer.addShortArticle(shortArticle);
        }
        shortArticlesAdapter.notifyDataSetChanged();
    }
}