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
    protected void onPostExecute(String articleText) {
        progressDialog.dismiss();

        String start = "<html><head><style>img { width: 100%; height: auto; };</style></head><body>";
        String end = "</body></html>";
        String result = start + articleText + end;
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
    }
}