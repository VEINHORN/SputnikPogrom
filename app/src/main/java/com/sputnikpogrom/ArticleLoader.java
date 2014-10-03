package com.sputnikpogrom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleLoader extends AsyncTask<String, Integer, String> {
    private WebView webView;
    private String articleUrl;
    private Context context;
    private ProgressDialog progressDialog;

    public ArticleLoader(Context context, WebView webView, String articleUrl) {
        this.context = context;
        this.webView = webView;
        this.articleUrl = articleUrl;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Загрузка", "Подождите. Статья загружается.", true);
    }

    @Override
    protected String doInBackground(String... params) {
        return new ArticleFetcher(articleUrl).getArticle();
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.hide();
        webView.loadData(result, "text/html; charset=UTF-8", null);
    }
}