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
    private final static String URL = "http://sputnik.t30p.ru";
    private final static String PAGE = "?page=";
    private final static int ARTICLES_NUM = 7; // 7 * 9 = 63 last articles to show

    public ShortArticlesContainer getArticles() {
        ShortArticlesContainer shortArticles = new ShortArticlesContainer();
        try {
            String url = "";
            for(int i = 1; i <= ARTICLES_NUM; i++) {
                if(i == 1) {
                    url = URL;
                } else {
                    url = URL + PAGE + Integer.toString(i);
                }

                Document document = Jsoup.connect(url).get();
                Elements liArticles = document.getElementsByClass("posts").get(0).getElementsByTag("li");

                for(Element element : liArticles) {
                    ShortArticle shortArticle = new ShortArticle();
                    shortArticle.setTitle(element.getElementsByTag("h3").get(0).text());
                    shortArticle.setUrl(URL + element.getElementsByTag("a").get(0).attr("href"));
                    shortArticles.addShortArticle(shortArticle);
                }
            }
        } catch (IOException e) {

        }
        return shortArticles;
    }
}