package com.example.news.movenews.model;

import java.util.List;

/**
 * Created by Yoe on 2015/10/17.
 */
public class FocusNewList {

    private String total;
    private List<FocusNewListResult> tngou;

    public List<FocusNewListResult> getTngou() {
        return tngou;
    }

    public void setTngou(List<FocusNewListResult> tngou) {
        this.tngou = tngou;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public class FocusNewListResult{

        private String keywords;
        private String description;
        private String img;
        private String fromurl;

        public FocusNewListResult(String keywords, String description, String img, String fromurl) {
            this.keywords = keywords;
            this.description = description;
            this.img = img;
            this.fromurl = fromurl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getFromurl() {
            return fromurl;
        }

        public void setFromurl(String fromurl) {
            this.fromurl = fromurl;
        }
    }
}
