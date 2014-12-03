package com.sputnikpogrom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sputnikpogrom.R;
import com.sputnikpogrom.adapters.ShortArticlesAdapter;
import com.sputnikpogrom.entities.ShortArticlesContainer;
import com.sputnikpogrom.loaders.SPLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.articlesListView) ListView articlesListView;
    @InjectView(R.id.adView) AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        adView.loadAd(new AdRequest.Builder().build());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mainmenu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return false;
    }
}