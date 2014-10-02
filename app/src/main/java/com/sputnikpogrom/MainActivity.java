package com.sputnikpogrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private ListView articlesListView;
    private TextView testTextViev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesListView = (ListView)findViewById(R.id.articlesListView);
        final ShortArticlesContainer shortArticlesContainer = new ShortArticlesContainer();
        ShortArticlesAdapter shortArticlesAdapter = new ShortArticlesAdapter(this, shortArticlesContainer);
        articlesListView.setAdapter(shortArticlesAdapter);
        SPLoader spLoader = new SPLoader(articlesListView, shortArticlesContainer, shortArticlesAdapter);
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