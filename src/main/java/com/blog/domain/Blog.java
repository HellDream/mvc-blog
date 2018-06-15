package com.blog.domain;

import java.util.ArrayList;

public class Blog {
    private int articleId;
    private String title;
    private String passage;
    private ArrayList<Tag> tags;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassage() {
        return passage;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
