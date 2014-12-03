package com.sputnikpogrom.loaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.adapters.ShortArticlesAdapter;
import com.sputnikpogrom.entities.ShortArticlesContainer;
import com.sputnikpogrom.fetchers.SPFetcher;

/**
 * Created by veinhorn on 2.10.14.
 */
public class SPLoader extends AsyncTask<String, String, ShortArticlesContainer> {
    private ListView articlesListView;
    private ShortArticlesContainer shortArticlesContainer;
    private ShortArticlesAdapter shortArticlesAdapter;
    private Context context;
    private ProgressDialog progressDialog;

    public SPLoader(Context context, ListView articlesListView, ShortArticlesContainer shortArticlesContainer, ShortArticlesAdapter shortArticlesAdapter) {
        this.context = context;
        this.articlesListView = articlesListView;
        this.shortArticlesContainer = shortArticlesContainer;
        this.shortArticlesAdapter = shortArticlesAdapter;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Загрузка", "Подождите.Загружается список статей.", true);
    }

    @Override
    protected ShortArticlesContainer doInBackground(String... args) {
        return new SPFetcher(context).getArticles();
    }

    @Override
    protected void onPostExecute(ShortArticlesContainer shortArticles) {
        for(int i = 0; i < shortArticles.size(); i++) {
            ShortArticle shortArticle = new ShortArticle();
            shortArticle.setTitle(shortArticles.getShortArticle(i).getTitle());
            shortArticle.setUrl(shortArticles.getShortArticle(i).getUrl());
            shortArticle.setPosterUrl(shortArticles.getShortArticle(i).getPosterUrl());
            shortArticlesContainer.addShortArticle(shortArticle);
        }

        progressDialog.dismiss();
        shortArticlesAdapter.notifyDataSetChanged();
    }
}