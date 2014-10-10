package com.sputnikpogrom;

import java.util.ArrayList;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ShortArticlesContainer {
    private ArrayList<ShortArticle> shortArticles;

    public ShortArticlesContainer() {
        shortArticles = new ArrayList<ShortArticle>();
    }

    public void addShortArticle(ShortArticle shortArticle) {
        shortArticles.add(shortArticle);
    }

    public ShortArticle getShortArticle(int index) {
        return shortArticles.get(index);
    }

    public int size() {
        return shortArticles.size();
    }
}