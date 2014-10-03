package com.sputnikpogrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends ActionBarActivity {
    private ListView articlesListView;
    private TextView testTextViev;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = (AdView)findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());

        articlesListView = (ListView)findViewById(R.id.articlesListView);
        final ShortArticlesContainer shortArticlesContainer = new ShortArticlesContainer();
        ShortArticlesAdapter shortArticlesAdapter = new ShortArticlesAdapter(this, shortArticlesContainer);
        articlesListView.setAdapter(shortArticlesAdapter);
        SPLoader spLoader = new SPLoader(this, articlesListView, shortArticlesContainer, shortArticlesAdapter);
        spLoader.execute();

        articlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("articleUrl", shortArticlesContainer.getShortArticle(position).getUrl());
                startActivity(intent);
            }
        });
    }
}