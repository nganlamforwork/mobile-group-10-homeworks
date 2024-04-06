package com.example.myapplication;

public class RSSItem {
    public String title;
    public String link;
    public String pubdate;

    public RSSItem(){

    }

    public RSSItem(String title, String link, String pubdate) {
        this.title = title;
        this.link = link;
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}