package com.sputnikpogrom;

/**
 * Created by veinhorn on 2.10.14.
 */
public class ShortArticle {
    private String title;
    private String shortText;
    private String url;

    public ShortArticle() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShortArticle(String title, String shortText) {
        this.title = title;
        this.shortText = shortText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }
}
