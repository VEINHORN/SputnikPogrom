package com.sputnikpogrom.fetchers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ArticleFetcher {
    private String articleUrl;

    public ArticleFetcher(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticle() {
        String result = "";
        try {
            Document document = Jsoup.connect(articleUrl).get();
            result = document.getElementById("post0").html();
        } catch(IOException e) {

        }
        return result;
    }
}