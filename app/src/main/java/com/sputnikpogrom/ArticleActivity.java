package com.sputnikpogrom;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleActivity extends ActionBarActivity {
    private WebView webView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        adView = (AdView)findViewById(R.id.articleAdView);
        adView.loadAd(new AdRequest.Builder().build());

        webView = (WebView)findViewById(R.id.webview);

        String articleUrl = getIntent().getStringExtra("articleUrl");
        ArticleLoader articleLoader = new ArticleLoader(this, webView, articleUrl);
        articleLoader.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.getSettings().setBuiltInZoomControls(false);
    }
}