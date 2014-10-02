package com.sputnikpogrom;

import android.os.AsyncTask;
import android.webkit.WebView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleLoader extends AsyncTask<String, Integer, String> {
    private WebView webView;
    private String articleUrl;

    public ArticleLoader(WebView webView, String articleUrl) {
        this.webView = webView;
        this.articleUrl = articleUrl;
    }

    @Override
    protected String doInBackground(String... params) {
        return new ArticleFetcher(articleUrl).getArticle();
    }

    @Override
    protected void onPostExecute(String result) {
        webView.loadData(result, "text/html; charset=UTF-8", null);
    }
}
