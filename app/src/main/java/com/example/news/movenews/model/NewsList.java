package com.example.news.movenews.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yoe on 2015/10/15.
 */
public class NewsList implements Serializable{

    private String code;
    private String msg;
    private List<NewsResultList> newslist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsResultList> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsResultList> newslist) {
        this.newslist = newslist;
    }

    public static class NewsResultList {

        private String hottime;
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getHottime() {
            return hottime;
        }

        public void setHottime(String hottime) {
            this.hottime = hottime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public NewsResultList(String hottime,String ctime,String title, String description, String picUrl, String url) {
            this.hottime = hottime;
            this.ctime = ctime;
            this.title = title;
            this.description = description;
            this.picUrl = picUrl;
            this.url = url;
        }

    }
}
