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

    private static String URL = "http://sputnik.t30p.ru";

    public ShortArticlesContainer getArticles() {
        String result = "";
        ShortArticlesContainer shortArticles = new ShortArticlesContainer();
        try {
            Document document = Jsoup.connect("http://sputnik.t30p.ru/").get();
            Elements liArticles = document.getElementsByClass("posts").get(0).getElementsByTag("li");

            //result = liArticles.get(0).html();
            //result = URL + liArticles.get(0).getElementsByTag("a").get(0).attr("href");

            for(Element element : liArticles) {
                ShortArticle shortArticle = new ShortArticle();
                shortArticle.setTitle(element.getElementsByTag("h3").get(0).text());
                shortArticle.setUrl(URL + element.getElementsByTag("a").get(0).attr("href"));
                shortArticles.addShortArticle(shortArticle);
            }
        } catch (IOException e) {

        }
        return shortArticles;
        //return result;
    }
}