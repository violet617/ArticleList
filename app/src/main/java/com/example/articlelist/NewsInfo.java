package com.example.articlelist;

public class NewsInfo {
    private int id;
    private String title;
    private String shareUser;
    private String chapterName;
    private String niceDate;
    private String link;
    private boolean collcet;

    @Override
    public String toString() {
        return "NewsInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shareUser='" + shareUser + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", link='" + link + '\'' +
                ", collcet=" + collcet +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isCollcet() {
        return collcet;
    }

    public void setCollcet(boolean collcet) {
        this.collcet = collcet;
    }

    public NewsInfo() {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public NewsInfo(String icon, String title, String author, String chapterName, String niceDate,String link) {
        this.shareUser =author;
        this.niceDate =niceDate;
        this.chapterName=chapterName;
        this.title=title;
        this.link=link;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }
}
