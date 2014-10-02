package com.sputnikpogrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
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

        //testTextViev = (TextView)findViewById(R.id.testTextView);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}