package Models;

import java.util.Date;

public class Article {
    public int id;
    public String user;
    public int journalId;
    public String summary;
    public Date date;

    public Article(int id, String user, int journalId, String summary, Date date) {
        this.id = id;
        this.user = user;
        this.journalId = journalId;
        this.summary = summary;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
