package com.neoniou.timaner.pojo;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author neo.zzj
 * @date 2019-12-2
 */
@Table(name = "calender")
public class Calender {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int id;
    private String sun;
    private String date;
    private String moon;
    private String words;
    private String author;
    private String festival;
    private String index;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Calender{" +
                "id=" + id +
                ", sun='" + sun + '\'' +
                ", date='" + date + '\'' +
                ", moon='" + moon + '\'' +
                ", words='" + words + '\'' +
                ", author='" + author + '\'' +
                ", festival='" + festival + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
