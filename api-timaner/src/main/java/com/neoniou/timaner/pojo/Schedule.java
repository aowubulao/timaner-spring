package com.neoniou.timaner.pojo;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author neo.zzj
 * @date 2019-11-26
 */
@Table(name = "schedule")
public class Schedule {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int sid;
    private String title;
    private String date;
    private int uid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sid=" + sid +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", uid=" + uid +
                '}';
    }
}
