package com.sputnikpogrom.fetchers;

import android.content.Context;
import android.preference.PreferenceManager;

import com.sputnikpogrom.R;
import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.entities.ShortArticlesContainer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 2.10.14.
 */
public class SPFetcher {
    private final static String URL = "http://sputnik.t30p.ru", PAGE = "?page=";
    private final static int TOTAL_ARTICLES_NUM = 35; // 35 * 10 = 350 articles total
    private Context context;

    public SPFetcher(Context context) {
        this.context = context;
    }

    public ShortArticlesContainer getArticles() {
        ShortArticlesContainer shortArticles = new ShortArticlesContainer();
        try {
            String articlesNumStr = PreferenceManager.getDefaultSharedPreferences(context).getString("num_of_articles", context.getString(R.string.default_num_of_articles));
            int articlesNum = Integer.parseInt(articlesNumStr);
            int articlesNumForCouner = articlesNum;
            // Here user gets only articles that he set in preferences
            if(articlesNum <= 10) {
                articlesNum = 1;
            } else if(articlesNum > 10 && articlesNum <= TOTAL_ARTICLES_NUM * 10) {
                articlesNum = articlesNum / 10 + 1;
            } else {
                context.getString(R.string.default_num_of_articles);
            }

            String url = "";
            int counter = 1; // just for exit
            for(int i = 1; i <= articlesNum; i++) {
                if(i == 1)
                    url = URL;
                else
                    url = URL + PAGE + Integer.toString(i);

                Document document = Jsoup.connect(url).get();
                Elements liArticles = document.getElementsByClass("posts").get(0).getElementsByTag("li");

                for(Element element : liArticles) {
                    ShortArticle shortArticle = new ShortArticle();
                    shortArticle.setTitle(element.getElementsByTag("h3").get(0).text());
                    shortArticle.setUrl(URL + element.getElementsByTag("a").get(0).attr("href"));

                    Elements imgElements = element.getElementsByTag("img");
                    if(imgElements.isEmpty())
                        shortArticle.setPosterUrl(null); // if we cannot get image from short description on site
                    else
                        shortArticle.setPosterUrl(element.getElementsByTag("img").get(0).attr("src"));
                    shortArticles.addShortArticle(shortArticle);

                    if(counter++ == articlesNumForCouner) return shortArticles;
                }
            }
        } catch (IOException e) {

        }
        return shortArticles;
    }
}