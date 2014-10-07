package com.sputnikpogrom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ShortArticlesAdapter extends BaseAdapter {
    private static class ViewHolder {
        @InjectView(R.id.short_article_title) TextView title;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    private Context context;
    private ShortArticlesContainer shortArticlesContainer;
    private LayoutInflater layoutInflater;

    public ShortArticlesAdapter(Context context, ShortArticlesContainer shortArticlesContainer) {
        this.context = context;
        this.shortArticlesContainer = shortArticlesContainer;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return shortArticlesContainer.size();
    }

    public Object getItem(int position) {
        return shortArticlesContainer.getShortArticle(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.short_article_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(shortArticlesContainer.getShortArticle(position).getTitle());
        return convertView;
    }
}
