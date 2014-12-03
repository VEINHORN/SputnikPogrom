package com.sputnikpogrom.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sputnikpogrom.R;
import com.sputnikpogrom.loaders.ArticleLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleActivity extends ActionBarActivity {
    @InjectView(R.id.webview) WebView webView;
    @InjectView(R.id.articleAdView) AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);

        adView.loadAd(new AdRequest.Builder().build());

        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(true);

        ArticleLoader articleLoader = new ArticleLoader(this, webView, getIntent().getStringExtra("articleUrl"));
        articleLoader.execute();
    }

    @Override
    protected void onDestroy() {
        webView.setVisibility(View.GONE); // bug with zoom buttons without this line
        super.onDestroy();
    }
}