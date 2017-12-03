package com.bwie.lihanxiang20171201.view;

/**
 * Created by Lonely on 2017/12/1.
 */
public class EventBean {
    public String url;
    public String title;

    public EventBean(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
