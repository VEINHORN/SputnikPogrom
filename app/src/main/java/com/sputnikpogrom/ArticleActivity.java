package com.sputnikpogrom;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleActivity extends ActionBarActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        webView = (WebView)findViewById(R.id.webview);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        String articleUrl = getIntent().getStringExtra("articleUrl");
        //Toast.makeText(this, articleUrl, Toast.LENGTH_SHORT).show();
        //String str = "<html><body>You scored <b>192</b> points.</body></html>";
        //webView.loadData(str, "text/html", null);
        //webView.loadUrl(articleUrl);
        ArticleLoader articleLoader = new ArticleLoader(webView, articleUrl);
        articleLoader.execute();
    }
}
