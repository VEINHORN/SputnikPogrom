package com.sputnikpogrom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 2.10.14.
 */
public class SPFetcher {

    public ShortArticlesContainer getArticles() {
        ShortArticlesContainer shortArticles = new ShortArticlesContainer();
        try {
            Document document = Jsoup.connect("http://sputnik.t30p.ru/").get();
            Elements liArticles = document.getElementsByClass("posts").get(0).getElementsByTag("li");

            for(Element element : liArticles) {
                ShortArticle shortArticle = new ShortArticle();
                shortArticle.setTitle(element.getElementsByTag("h3").get(0).text());
                shortArticles.addShortArticle(shortArticle);
            }
        } catch (IOException e) {

        }
        return shortArticles;
    }
}